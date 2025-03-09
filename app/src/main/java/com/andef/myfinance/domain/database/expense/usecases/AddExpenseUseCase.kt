package com.andef.myfinance.domain.database.expense.usecases

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(expense: Expense) {
        repository.addExpense(expense)
    }
}