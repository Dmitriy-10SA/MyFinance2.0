package com.andef.myfinance.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.mainScreenNavGraph(
    incomesScreenContent: @Composable () -> Unit,
    expensesScreenContent: @Composable () -> Unit,
    totalsScreenContent: @Composable () -> Unit
) {
    navigation(
        route = Screen.MainScreen.route,
        startDestination = Screen.MainScreen.IncomesScreen.route
    ) {
        composable(Screen.MainScreen.IncomesScreen.route) {
            incomesScreenContent()
        }
        composable(Screen.MainScreen.ExpensesScreen.route,) {
            expensesScreenContent()
        }
        composable(Screen.MainScreen.TotalsScreen.route) {
            totalsScreenContent()
        }
    }
}