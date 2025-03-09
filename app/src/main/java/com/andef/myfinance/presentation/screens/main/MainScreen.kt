package com.andef.myfinance.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            MainBottomNavigation()
        }
    ) {

    }
}

@Composable
fun MainBottomNavigation() {
    val items = listOf(
        NavigationItem.Income,
        NavigationItem.Expenses,
        NavigationItem.Totals
    )
    NavigationBar {
        for (item in items) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.imageResId),
                        contentDescription = stringResource(item.nameResId)
                    )
                },
                label = { Text(stringResource(item.nameResId)) },
                selected = false,
                onClick = {

                },
                alwaysShowLabel = false
            )
        }
    }
}