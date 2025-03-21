package com.andef.myfinance.domain.expense.usecases

import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import java.util.Date
import javax.inject.Inject

class ChangeExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Date
    ) {
        repository.changeExpense(id, amount, category, comment, date)
    }
}