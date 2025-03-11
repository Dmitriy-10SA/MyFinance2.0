package com.andef.myfinance.presentation.ui.main

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.income.entities.Income

sealed class MainScreenState {
    data object DatePickerScreen : MainScreenState()
    data object AnyScreenWithTopAndBottomNav : MainScreenState()
    data object IncomeScreenForAdd: MainScreenState()
    data object ExpenseScreenForAdd: MainScreenState()
    data class IncomeScreenForChange(val income: Income): MainScreenState()
    data class ExpenseScreenForChange(val expense: Expense): MainScreenState()
}