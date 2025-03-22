package com.andef.myfinance.utils

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.myfinance.navigation.NavigationState

@Composable
fun getCurrentDestination(navigationState: NavigationState): NavDestination? {
    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    return currentDestination
}