package com.andef.myfinance.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    incomesScreenContent: @Composable () -> Unit,
    expensesScreenContent: @Composable () -> Unit,
    totalsScreenContent: @Composable () -> Unit,
    rangeDatePickerScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ) {
        mainScreenNavGraph(
            incomesScreenContent = incomesScreenContent,
            expensesScreenContent = expensesScreenContent,
            totalsScreenContent = totalsScreenContent,
        )
        composable(
            route = Screen.RangeDatePickerScreen.route,
            enterTransition = {
                slideInVertically (
                    initialOffsetY = { -it },
                    animationSpec = tween(1000)
                )
            },
            exitTransition = {
                slideOutVertically (
                    targetOffsetY = { -it },
                    animationSpec = tween(1000)
                ) + fadeOut(tween(1000))
            }
        ) {
            rangeDatePickerScreen()
        }
    }
}