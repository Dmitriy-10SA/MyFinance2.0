package com.andef.myfinance.domain.database.expense.repository

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
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
    fun getExpenses(startDate: Date, endDate: Date): LiveData<List<Expense>>
    fun getFullAmount(startDate: Date, endDate: Date): LiveData<Double>
    suspend fun getExpensesAmount(expenses: List<Expense>): List<Double>
}