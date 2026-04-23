package com.example.fit5046a2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit5046a2.screens.DashboardScreen
import com.example.fit5046a2.screens.HistoryScreen
import com.example.fit5046a2.screens.SettingsScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onLogoutClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.DASHBOARD,
        modifier = modifier
    ) {
        composable(NavRoutes.DASHBOARD) {
            DashboardScreen()
        }

        composable(NavRoutes.HISTORY) {
            HistoryScreen()
        }

        composable(NavRoutes.SETTINGS) {
            SettingsScreen(
                onLogoutClick = onLogoutClick
            )
        }
    }
}
