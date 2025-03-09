package com.andef.myfinance.domain.database.expense.usecases

import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import javax.inject.Inject

class RemoveExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(id: Int) {
        repository.removeExpense(id)
    }
}