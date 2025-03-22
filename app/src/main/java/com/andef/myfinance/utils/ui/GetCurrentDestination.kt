package com.andef.myfinance.utils.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.myfinance.navigation.main.MainNavigationState

@Composable
internal fun getCurrentDestination(mainNavigationState: MainNavigationState): NavDestination? {
    val navBackStackEntry = mainNavigationState.navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    return currentDestination
}