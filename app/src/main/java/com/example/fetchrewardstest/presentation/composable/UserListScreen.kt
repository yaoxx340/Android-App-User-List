package com.example.fetchrewardstest.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fetchrewardstest.domain.model.User
import com.example.fetchrewardstest.presentation.state.UserState
import com.example.fetchrewardstest.presentation.viewmodel.UserViewModel

@Composable
fun UserListScreen(userState: UserState? = null) {
    val viewModel: UserViewModel = hiltViewModel()
    val observedUserState by viewModel.userState.observeAsState()
    val finalState = userState ?: observedUserState

    when (finalState) {
        is UserState.Loading -> LoadingView()
        is UserState.Success -> {
            val successState = finalState as? UserState.Success
            if (successState?.users.isNullOrEmpty()) {
                EmptyListView()
            } else {
                UserListView(users = successState?.users ?: listOf())
            }
        }
        is UserState.Error -> ErrorView((finalState as UserState.Error).message)
        else -> EmptyListView()
    }
}

@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Text(
                text = "List ID: ${user.listId}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "ID: ${user.id}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Text(
                text = "Name: ${user.name.orEmpty()}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun UserListView(users: List<User>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(users) { user ->
            UserItem(user = user)
        }
    }
}

@Composable
fun LoadingView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, modifier = Modifier.testTag("loadingIndicator"))
    }
}

@Composable
fun EmptyListView() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text("No users found", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun ErrorView(message: String) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Text(message, color = Color.Red, style = MaterialTheme.typography.bodyLarge)
    }
}
