package com.example.fit5046a2.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fit5046a2.screens.MainScreen
import com.example.fit5046a2.screens.LoginScreen
import com.example.fit5046a2.screens.RegisterScreen

@Composable
fun CardioGuardNavGraph(
    navController: NavHostController,
    startDestination: String = NavRoutes.LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(NavRoutes.DASHBOARD) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(NavRoutes.REGISTER)
                }
            )
        }

        composable(route = NavRoutes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(NavRoutes.DASHBOARD) {
                        popUpTo(NavRoutes.REGISTER) { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavRoutes.DASHBOARD) {
            MainScreen(
                onLogoutClick = {
                    navController.navigate(NavRoutes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}


