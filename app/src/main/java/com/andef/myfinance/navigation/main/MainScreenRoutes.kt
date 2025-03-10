package com.andef.myfinance.navigation.main

sealed class MainScreenRoutes(val route: String) {
    data object IncomesScreen : MainScreenRoutes(INCOMES_SCREEN_ROUTE)
    data object ExpensesScreen : MainScreenRoutes(EXPENSES_SCREEN_ROUTE)
    data object TotalsScreen : MainScreenRoutes(TOTALS_SCREEN_ROUTE)

    companion object {
        const val INCOMES_SCREEN_ROUTE = "incomes"
        const val EXPENSES_SCREEN_ROUTE = "expenses"
        const val TOTALS_SCREEN_ROUTE = "totals"
    }
}