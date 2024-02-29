package com.example.fetchrewardstest.data.remote

import com.example.fetchrewardstest.domain.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("hiring.json")
    suspend fun getUsers(): List<User>
}