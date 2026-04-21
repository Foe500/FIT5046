package com.example.fit5046a2.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit5046a2.screens.DashboardScreen


@Composable
fun CardioGuardNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.LOGIN // App 启动时首先进入登录页
    ) {
        composable(NavRoutes.LOGIN) {
            // 稍后我们会在这里填充 LoginScreen
        }
        composable(NavRoutes.REGISTER) {
            // 稍后填充 RegisterScreen
        }
        composable(NavRoutes.DASHBOARD) {
            // 稍后填充 DashboardScreen
            DashboardScreen(
                onNavigateToHistory = {
                    navController.navigate(NavRoutes.HISTORY)
                }
            )
        }
        composable(NavRoutes.HISTORY) {
            // 稍后填充 HistoryScreen
        }
    }
}