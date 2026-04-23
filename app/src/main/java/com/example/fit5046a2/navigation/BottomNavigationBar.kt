package com.example.fit5046a2.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fit5046a2.screens.DashboardScreen
import com.example.fit5046a2.ui.theme.FIT5046A2Theme

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavRoutes.DASHBOARD,
        NavRoutes.HISTORY,
        NavRoutes.SETTINGS
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Text(screen.first().toString())
                },
                label = { Text(screen) },
                selected = currentRoute == screen,
                onClick = {
                    navController.navigate(screen) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun BottomNavGraphPreview() {
    FIT5046A2Theme {
        BottomNavigationBar(navController = NavHostController(LocalContext.current))
    }
}
