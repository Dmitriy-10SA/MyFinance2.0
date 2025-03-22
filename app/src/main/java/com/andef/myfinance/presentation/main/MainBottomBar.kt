package com.andef.myfinance.presentation.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andef.myfinance.navigation.NavigationState
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.utils.isInHierarchy

@Composable
fun MainBottomBar(navigationState: NavigationState) {
    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination

    if (isInHierarchy(currentDestination, Screen.MainScreen.route) &&
        currentDestination?.route != Screen.RangeDatePickerScreen.route
    ) {
        val items = listOf(
            MainBottomBarItem.Incomes,
            MainBottomBarItem.Expenses,
            MainBottomBarItem.Totals
        )
        NavigationBar(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    ambientColor = MaterialTheme.colorScheme.onBackground
                ),
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ) {
            for (item in items) {
                NavigationBarItem(
                    selected = isInHierarchy(currentDestination, item.route),
                    onClick = {
                        navigationState.navigateTo(item.route)
                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.iconResId),
                            contentDescription = stringResource(item.contentDescriptionResId)
                        )
                    },
                    label = {
                        Text(text = stringResource(item.titleResId))
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Transparent,
                        selectedTextColor = MaterialTheme.colorScheme.onBackground,
                        selectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground
                    ),
                    alwaysShowLabel = false
                )
            }
        }
    }
}