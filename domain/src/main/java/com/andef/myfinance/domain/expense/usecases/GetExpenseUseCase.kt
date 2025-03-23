package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import javax.inject.Inject

class GetExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(id: Int): Expense {
        return repository.getExpense(id)
    }
}