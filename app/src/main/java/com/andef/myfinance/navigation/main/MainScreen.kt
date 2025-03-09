package com.andef.myfinance.navigation.main

sealed class MainScreen(val route: String) {
    data object IncomesScreen: MainScreen(INCOMES_SCREEN_ROUTE)
    data object ExpensesScreen: MainScreen(EXPENSES_SCREEN_ROUTE)
    data object TotalsScreen: MainScreen(TOTALS_SCREEN_ROUTE)

    companion object {
        const val INCOMES_SCREEN_ROUTE = "incomes"
        const val EXPENSES_SCREEN_ROUTE = "expenses"
        const val TOTALS_SCREEN_ROUTE = "totals"
    }
}