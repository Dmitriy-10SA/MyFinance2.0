package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import javax.inject.Inject

class RemoveExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(id: Int) {
        repository.removeExpense(id)
    }
}