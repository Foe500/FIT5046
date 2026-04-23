package com.example.fit5046a2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit5046a2.screens.DashboardScreen
import com.example.fit5046a2.screens.HistoryScreen
import com.example.fit5046a2.screens.SettingsScreen
import com.example.fit5046a2.ui.theme.FIT5046A2Theme

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.DASHBOARD
    ) {

        composable(NavRoutes.DASHBOARD) {
            DashboardScreen(
//                onNavigateToHistory = {
//                    navController.navigate(NavRoutes.HISTORY)
//                }
            )
        }

        composable(NavRoutes.HISTORY) {
            HistoryScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.SETTINGS) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

