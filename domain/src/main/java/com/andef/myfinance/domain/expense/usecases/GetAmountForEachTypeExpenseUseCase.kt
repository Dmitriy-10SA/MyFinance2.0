package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import javax.inject.Inject

class GetAmountForEachTypeExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(expenses: List<Expense>): List<Double> {
        return repository.getAmountForEachTypeExpense(expenses)
    }
}