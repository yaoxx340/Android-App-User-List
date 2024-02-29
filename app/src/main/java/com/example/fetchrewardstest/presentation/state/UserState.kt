package com.example.fetchrewardstest.presentation.state

import com.example.fetchrewardstest.domain.model.User

sealed class UserState {
    object Loading : UserState()
    object Empty : UserState()
    data class Success(val users: List<User>) : UserState()
    data class Error(val message: String) : UserState()
}
