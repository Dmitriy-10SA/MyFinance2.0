package com.andef.myfinance.presentation.ui.expense

import com.andef.myfinance.domain.database.expense.entities.Expense

sealed class ExpensesState {
    data object Initial : ExpensesState()
    data object Loading : ExpensesState()
    data class Expenses(val expenses: List<Expense>) : ExpensesState()
    data class Amount(val amount: Double) : ExpensesState()
    data object Error : ExpensesState()
}