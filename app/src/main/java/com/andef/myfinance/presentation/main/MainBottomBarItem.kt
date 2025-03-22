package com.andef.myfinance.presentation.main

import com.andef.myfinance.R
import com.andef.myfinance.navigation.Screen

sealed class MainBottomBarItem(
    val titleResId: Int,
    val iconResId: Int,
    val contentDescriptionResId: Int,
    val route: String
) {
    data object Incomes : MainBottomBarItem(
        titleResId = R.string.incomes,
        iconResId = R.drawable.incomes,
        contentDescriptionResId = R.string.incomes,
        route = Screen.MainScreen.Incomes.route
    )

    data object Expenses : MainBottomBarItem(
        titleResId = R.string.expenses,
        iconResId = R.drawable.expenses,
        contentDescriptionResId = R.string.expenses,
        route = Screen.MainScreen.Expenses.route
    )

    data object Totals : MainBottomBarItem(
        titleResId = R.string.totals,
        iconResId = R.drawable.totals,
        contentDescriptionResId = R.string.totals,
        route = Screen.MainScreen.Totals.route
    )
}