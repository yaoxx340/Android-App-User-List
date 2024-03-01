package com.example.fetchrewardstest.data

import com.example.fetchrewardstest.data.remote.UserApiService
import com.example.fetchrewardstest.data.repository.UserRepository
import com.example.fetchrewardstest.domain.model.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserRepositoryTest {
    private lateinit var apiService: UserApiService
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        apiService = mockk()
        userRepository = UserRepository(apiService)
    }

    @Test
    fun `getFilteredAndSortedUsers returns filtered and sorted list`(): Unit = runTest {
        val users = listOf(
            User(id = 1, listId = 1, name = "Alice"),
            User(id = 2, listId = 2, name = ""),
            User(id = 3, listId = 1, name = "Bob")
        )

        coEvery { apiService.getUsers() } returns users

        val expected = listOf(
            User(id = 1, listId = 1, name = "Alice"),
            User(id = 3, listId = 1, name = "Bob")
        )

        val result = userRepository.getFilteredAndSortedUsers()

        assertEquals(expected, result)
    }

    @Test
    fun `getFilteredAndSortedUsers handles exceptions gracefully`() = runTest {
        coEvery { apiService.getUsers() } throws Exception("Network error")

        val result = userRepository.getFilteredAndSortedUsers()

        assertEquals(emptyList<User>(), result)
    }
}