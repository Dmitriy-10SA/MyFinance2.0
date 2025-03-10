package com.andef.myfinance.domain.database.expense.repository

import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)
    suspend fun changeExpense(
        expense: Expense,
        newAmount: Double? = null,
        newCategory: ExpenseCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    )

    suspend fun removeExpense(id: Int)
    fun getExpenses(startDate: Date, endDate: Date): Flow<List<Expense>>
    fun getFullAmount(startDate: Date, endDate: Date): Flow<Double>
}