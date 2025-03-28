package com.andef.myfinance.navigation.main

sealed class Screen(val route: String) {
    data object MainScreen : Screen(MAIN_SCREEN_ROUTE) {
        data object Incomes : Screen(INCOMES_ROUTE)
        data object Expenses : Screen(EXPENSES_ROUTE)
        data object Totals : Screen(TOTALS_ROUTE)

        private const val INCOMES_ROUTE = "incomes"
        private const val EXPENSES_ROUTE = "expenses"
        private const val TOTALS_ROUTE = "totals"
    }

    companion object {
        private const val MAIN_SCREEN_ROUTE = "main_screen"
    }
}