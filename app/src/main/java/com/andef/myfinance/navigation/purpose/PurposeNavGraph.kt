package com.andef.myfinance.navigation.purpose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.andef.myfinance.navigation.slideInLeft
import com.andef.myfinance.navigation.slideInRight
import com.andef.myfinance.navigation.slideOutLeft
import com.andef.myfinance.navigation.slideOutRight

@Composable
fun PurposeNavGraph(
    navHostController: NavHostController,
    activePurposeScreenContent: @Composable () -> Unit,
    completedPurposeScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = PurposeScreen.MainPurposeScreen.route
    ) {
        navigation(
            route = PurposeScreen.MainPurposeScreen.route,
            startDestination = PurposeScreen.MainPurposeScreen.ActivePurpose.route
        ) {
            composable(
                route = PurposeScreen.MainPurposeScreen.ActivePurpose.route,
                enterTransition = { slideInRight },
                exitTransition = { slideOutLeft }
            ) {
                activePurposeScreenContent()
            }
            composable(
                route = PurposeScreen.MainPurposeScreen.CompletedPurpose.route,
                enterTransition = { slideInLeft },
                exitTransition = { slideOutRight }
            ) {
                completedPurposeScreenContent()
            }
        }
    }
}