package com.example.fit5046a2.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.fit5046a2.navigation.BottomNavigationBar
import com.example.fit5046a2.navigation.BottomNavGraph

@Composable
fun MainScreen(
    onLogoutClick: () -> Unit
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = bottomNavController)
        }
    ) { innerPadding ->
        BottomNavGraph(
            navController = bottomNavController,
            modifier = Modifier.padding(innerPadding),
            onLogoutClick = onLogoutClick
        )
    }
}