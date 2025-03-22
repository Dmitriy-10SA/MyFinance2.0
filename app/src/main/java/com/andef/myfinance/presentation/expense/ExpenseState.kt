package com.andef.myfinance.presentation.expense

import com.andef.myfinance.domain.expense.entities.Expense

sealed class ExpenseState {
    data object Initial : ExpenseState()
    data object Loading : ExpenseState()
    data object Error : ExpenseState()
    data class ExpenseLoad(val expense: Expense?) : ExpenseState()
}