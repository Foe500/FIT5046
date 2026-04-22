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
            // LoginScreen
        }
        composable(NavRoutes.REGISTER) {
            //  RegisterScreen
        }
        composable(NavRoutes.DASHBOARD) {
            // DashboardScreen
            DashboardScreen(
                onNavigateToHistory = {
                    navController.navigate(NavRoutes.HISTORY)
                }
            )
        }
        composable(NavRoutes.HISTORY) {
            //  HistoryScreen
        }
    }
}