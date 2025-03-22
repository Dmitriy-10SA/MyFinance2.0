package com.andef.myfinance.utils.ui

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

internal fun isInHierarchy(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}