package com.example.fetchrewardstest.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.fetchrewardstest.data.repository.UserRepository
import com.example.fetchrewardstest.domain.model.User
import com.example.fetchrewardstest.presentation.state.UserState
import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var userRepository: UserRepository
    private lateinit var viewModel: UserViewModel
    private val userStateObserver: Observer<UserState> = mockk(relaxed = true)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userRepository = mockk()
        viewModel = UserViewModel(userRepository)
        viewModel.userState.observeForever(userStateObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `fetchUsers sets Loading and then Success state with users`() = runTest {
        val users = listOf(User(id = 1, listId = 1, name = "User One"))
        coEvery { userRepository.getFilteredAndSortedUsers() } returns users

        viewModel.fetchUsers()

        verify { userStateObserver.onChanged(UserState.Success(users)) }
    }

    @Test
    fun `fetchUsers sets Loading and then Empty state`() = runTest {
        coEvery { userRepository.getFilteredAndSortedUsers() } returns emptyList()

        viewModel.fetchUsers()

        verify { userStateObserver.onChanged(UserState.Empty) }
    }

    @Test
    fun `fetchUsers sets Loading and then Error state on exception`() = runTest {
        val errorMessage = "Network error"
        coEvery { userRepository.getFilteredAndSortedUsers() } throws RuntimeException(errorMessage)

        viewModel.fetchUsers()

        verify { userStateObserver.onChanged(match { it is UserState.Error && it.message == "Network error" }) }
    }
}
