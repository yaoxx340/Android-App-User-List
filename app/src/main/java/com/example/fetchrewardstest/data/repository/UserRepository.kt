package com.example.fetchrewardstest.data.repository

import com.example.fetchrewardstest.domain.model.User
import com.example.fetchrewardstest.data.remote.UserApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: UserApiService) {
    suspend fun getFilteredAndSortedUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUsers()
                response.filter { it.name?.isNotEmpty() == true }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}
