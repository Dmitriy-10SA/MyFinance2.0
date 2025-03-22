package com.andef.myfinance.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen(MAIN_SCREEN_ROUTE) {
        data object IncomesScreen : Screen(INCOME_SCREEN_ROUTE)
        data object ExpensesScreen : Screen(EXPENSE_SCREEN_ROUTE)
        data object TotalsScreen : Screen(TOTALS_SCREEN_ROUTE)
    }

    data object RangeDatePickerScreen : Screen(RANGE_DATE_PICKER_SCREEN_ROUTE)

    companion object {
        private const val MAIN_SCREEN_ROUTE = "main_screen_route"
        private const val INCOME_SCREEN_ROUTE = "income_screen_route"
        private const val EXPENSE_SCREEN_ROUTE = "expense_screen_route"
        private const val TOTALS_SCREEN_ROUTE = "totals_screen_route"
        private const val RANGE_DATE_PICKER_SCREEN_ROUTE = "range_date_picker_screen_route"
    }
}