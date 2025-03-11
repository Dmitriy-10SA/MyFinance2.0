package com.andef.myfinance.domain.database.expense.usecases

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import java.util.Date
import javax.inject.Inject

class ChangeExpenseUseCase @Inject constructor(
    private val repository: ExpenseRepository
) {
    suspend fun execute(
        expense: Expense,
        newAmount: Double? = null,
        newCategory: ExpenseCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    ) {
        repository.changeExpense(
            expense,
            newAmount,
            newCategory,
            newComment,
            newDate
        )
    }
}