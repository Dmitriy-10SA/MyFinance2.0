package com.andef.myfinance.navigation.purpose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class PurposeNavigationState(val navHostController: NavHostController) {
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
fun rememberPurposeNavigationState(
    navHostController: NavHostController = rememberNavController()
) : PurposeNavigationState {
    return remember {
        PurposeNavigationState(navHostController)
    }
}