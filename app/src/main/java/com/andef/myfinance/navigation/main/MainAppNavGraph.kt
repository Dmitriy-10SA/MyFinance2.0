package com.andef.myfinance.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainAppNavGraph(
    navHostController: NavHostController,
    incomesScreenContent: @Composable () -> Unit,
    expensesScreenContent: @Composable () -> Unit,
    totalsScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = MainScreenRoutes.IncomesScreen.route
    ) {
        composable(MainScreenRoutes.IncomesScreen.route) {
            incomesScreenContent()
        }
        composable(MainScreenRoutes.ExpensesScreen.route) {
            expensesScreenContent()
        }
        composable(MainScreenRoutes.TotalsScreen.route) {
            totalsScreenContent()
        }
    }
}