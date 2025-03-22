package com.andef.myfinance.utils

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

fun isInHierarchy(currentDestination: NavDestination?, route: String): Boolean {
    return currentDestination?.hierarchy?.any { it.route == route } == true
}