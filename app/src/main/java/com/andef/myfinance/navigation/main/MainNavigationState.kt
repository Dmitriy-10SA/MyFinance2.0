package com.andef.myfinance.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class MainNavigationState(val navHostController: NavHostController) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberMainNavigationState(
    navHostController: NavHostController = rememberNavController()
): MainNavigationState {
    return remember {
        MainNavigationState(navHostController)
    }
}