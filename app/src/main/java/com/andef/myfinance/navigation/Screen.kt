package com.andef.myfinance.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen(MAIN_SCREEN_ROUTE) {
        data object Incomes : Screen(INCOMES_ROUTE)
        data object Expenses : Screen(EXPENSES_ROUTE)
        data object Totals : Screen(TOTALS_ROUTE)

        private const val INCOMES_ROUTE = "incomes"
        private const val EXPENSES_ROUTE = "expenses"
        private const val TOTALS_ROUTE = "totals"
    }

    data object IncomeScreen : Screen(INCOME_SCREEN_ROUTE) {
        data object Income : Screen(INCOME_ROUTE)
        data object IncomeDatePicker : Screen(INCOME_DATE_PICKER)

        private const val INCOME_ROUTE = "income"
        private const val INCOME_DATE_PICKER = "incomeDatePicker"
    }

    data object ExpenseScreen : Screen(EXPENSE_SCREEN_ROUTE) {
        data object Expense : Screen(EXPENSE_ROUTE)
        data object ExpenseDatePicker : Screen(EXPENSE_DATE_PICKER_ROUTE)

        private const val EXPENSE_ROUTE = "expense"
        private const val EXPENSE_DATE_PICKER_ROUTE = "expenseDatePicker"
    }

    companion object {
        private const val MAIN_SCREEN_ROUTE = "main_screen"
        private const val INCOME_SCREEN_ROUTE = "income_screen"
        private const val EXPENSE_SCREEN_ROUTE = "expense_screen"
    }
}