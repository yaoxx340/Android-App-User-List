package com.example.fetchrewardstest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fetchrewardstest.presentation.composable.UserListScreen
import com.example.fetchrewardstest.presentation.ui.theme.FetchRewardsTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchRewardsTestTheme {
                UserListScreen()
            }
        }
    }
}

