package com.example.fetchrewardstest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchrewardstest.data.repository.UserRepository
import com.example.fetchrewardstest.presentation.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userState = MutableLiveData<UserState>()
    val userState: LiveData<UserState> = _userState

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        _userState.value = UserState.Loading
        viewModelScope.launch {
            try {
                val users = userRepository.getFilteredAndSortedUsers()
                if (users.isNotEmpty()) {
                    _userState.value = UserState.Success(users)
                } else {
                    _userState.value = UserState.Empty
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


