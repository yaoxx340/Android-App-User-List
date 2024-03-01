package com.example.fetchrewardstest.composable

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fetchrewardstest.domain.model.User
import com.example.fetchrewardstest.presentation.composable.EmptyListView
import com.example.fetchrewardstest.presentation.composable.ErrorView
import com.example.fetchrewardstest.presentation.composable.LoadingView
import com.example.fetchrewardstest.presentation.composable.UserListView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loadingState_displaysCircularProgressIndicator() {
        composeTestRule.setContent {
            LoadingView()
        }

        composeTestRule
            .onNodeWithTag("loadingIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun successState_displaysUsers() {
        composeTestRule.setContent {
            UserListView(users = listOf(User(1, 1, "User One"), User(2, 2, "User Two")))
        }

        composeTestRule.onNodeWithText("Name: User One").assertIsDisplayed()
        composeTestRule.onNodeWithText("Name: User Two").assertIsDisplayed()
    }

    @Test
    fun emptyState_displaysNoUsersFound() {
        composeTestRule.setContent {
            EmptyListView()
        }

        composeTestRule.onNodeWithText("No users found").assertIsDisplayed()
    }

    @Test
    fun errorState_displaysErrorMessage() {
        val errorMessage = "Network Error"
        composeTestRule.setContent {
            ErrorView(message = errorMessage)
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }
}
