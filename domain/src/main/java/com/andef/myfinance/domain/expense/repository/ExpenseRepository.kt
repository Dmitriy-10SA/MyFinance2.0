package com.andef.myfinance.domain.expense.repository

import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)

    suspend fun changeExpense(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Date
    )

    suspend fun removeExpense(id: Int)

    suspend fun getExpense(id: Int): Expense

    fun getExpenseList(startDate: Date, endDate: Date): Flow<List<Expense>>

    fun getFullExpenseAmount(startDate: Date, endDate: Date): Flow<Double>

    suspend fun getAmountForEachTypeExpense(expenses: List<Expense>): List<Double>
}