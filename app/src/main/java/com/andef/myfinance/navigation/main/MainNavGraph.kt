package com.andef.myfinance.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.andef.myfinance.navigation.slideInLeft
import com.andef.myfinance.navigation.slideInRight
import com.andef.myfinance.navigation.slideOutLeft
import com.andef.myfinance.navigation.slideOutRight

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    incomesScreenContent: @Composable () -> Unit,
    expensesScreenContent: @Composable () -> Unit,
    totalsScreenContent: @Composable () -> Unit
) {
    val lastDestination = remember {
        mutableStateOf(Screen.MainScreen.Incomes as Screen)
    }

    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ) {
        navigation(
            route = Screen.MainScreen.route,
            startDestination = Screen.MainScreen.Incomes.route
        ) {
            composable(
                route = Screen.MainScreen.Incomes.route,
                enterTransition = { slideInRight },
                exitTransition = { slideOutLeft }
            ) {
                incomesScreenContent()
                lastDestination.value = Screen.MainScreen.Incomes
            }
            composable(
                route = Screen.MainScreen.Expenses.route,
                enterTransition = {
                    if (lastDestination.value.route == Screen.MainScreen.Incomes.route) {
                        slideInLeft
                    } else {
                        slideInRight
                    }
                },
                exitTransition = {
                    if (navHostController.currentDestination?.route == Screen.MainScreen.Incomes.route) {
                        slideOutRight
                    } else {
                        slideOutLeft
                    }
                }
            ) {
                expensesScreenContent()
                lastDestination.value = Screen.MainScreen.Expenses
            }
            composable(
                route = Screen.MainScreen.Totals.route,
                enterTransition = { slideInLeft },
                exitTransition = { slideOutRight }
            ) {
                totalsScreenContent()
                lastDestination.value = Screen.MainScreen.Totals
            }
        }
    }
}