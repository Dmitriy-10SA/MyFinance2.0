package com.andef.myfinance.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.myfinance.R
import com.andef.myfinance.navigation.NavigationState
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White
import com.andef.myfinance.utils.getCurrentDestination

@Composable
fun MainFloatingActionButton(navigationState: NavigationState, isDarkTheme: Boolean) {
    val currentDestination = getCurrentDestination(navigationState)

    AnimatedVisibility(
        visible = currentDestination?.route in listOf(
            Screen.MainScreen.IncomesScreen.route,
            Screen.MainScreen.ExpensesScreen.route
        ),
        enter = scaleIn(tween(500)),
        exit = scaleOut(tween(500))
    ) {
        when (currentDestination?.route) {
            Screen.MainScreen.IncomesScreen.route -> {
                FAB(
                    isDarkTheme = isDarkTheme,
                    onFABClickListener = {
                        // Действие для доходов
                    }
                )
            }

            Screen.MainScreen.ExpensesScreen.route -> {
                FAB(
                    isDarkTheme = isDarkTheme,
                    onFABClickListener = {
                        // Действие для расходов
                    }
                )
            }
        }
    }
}

@Composable
private fun FAB(isDarkTheme: Boolean, onFABClickListener: () -> Unit) {
    FloatingActionButton(
        shape = CircleShape,
        containerColor = if (isDarkTheme) Blue else Orange,
        onClick = onFABClickListener
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = White,
            contentDescription = stringResource(R.string.button_for_add)
        )
    }
}