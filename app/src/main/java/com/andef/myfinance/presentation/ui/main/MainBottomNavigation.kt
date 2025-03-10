package com.andef.myfinance.presentation.ui.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.myfinance.navigation.main.NavigationState

@Composable
fun MainBottomNavigation(navigationState: NavigationState) {
    val items = listOf(
        BottomNavigationItem.Incomes,
        BottomNavigationItem.Expenses,
        BottomNavigationItem.Totals
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.shadow(16.dp)
    ) {
        val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry.value?.destination
        for (item in items) {
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onBackground,
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    indicatorColor = MaterialTheme.colorScheme.background,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                    disabledTextColor = MaterialTheme.colorScheme.onBackground,
                    disabledIconColor = MaterialTheme.colorScheme.onBackground
                ),
                icon = {
                    Icon(
                        painter = painterResource(item.imageResId),
                        contentDescription = stringResource(item.nameResId)
                    )
                },
                onClick = {
                    navigationState.mainNavigateTo(item.route)
                },
                label = {
                    Text(
                        text = stringResource(item.nameResId),
                        fontSize = 15.sp
                    )
                },
                selected = isInHierarchy(currentDestination, item),
                alwaysShowLabel = false
            )
        }
    }
}

private fun isInHierarchy(
    currentDestination: NavDestination?,
    item: BottomNavigationItem
): Boolean {
    return currentDestination?.hierarchy?.any { it.route == item.route } == true
}