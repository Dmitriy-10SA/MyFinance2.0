package com.andef.myfinance.domain.database.expense.usecases

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import javax.inject.Inject

class GetExpensesAmountUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(expenses: List<Expense>): List<Double> {
        return repository.getExpensesAmount(expenses)
    }
}