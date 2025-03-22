package com.andef.myfinance.navigation.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.navigation.slideInLeft
import com.andef.myfinance.navigation.slideInRight
import com.andef.myfinance.navigation.slideOutLeft
import com.andef.myfinance.navigation.slideOutRight

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    incomesScreenContent: @Composable () -> Unit,
    expensesScreenContent: @Composable () -> Unit,
    totalsScreenContent: @Composable () -> Unit,
) {
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
            }
            composable(
                route = Screen.MainScreen.Expenses.route,
                enterTransition = {
                    fadeIn(tween(1500))
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
            }
            composable(
                route = Screen.MainScreen.Totals.route,
                enterTransition = { slideInLeft },
                exitTransition = { slideOutRight }
            ) {
                totalsScreenContent()
            }
        }
    }
}