package com.andef.myfinance.domain.database.expense.entities

import java.util.Date

data class Expense(
    val id: Int = 0,
    val amount: Double,
    val category: ExpenseCategory,
    val comment: String,
    val date: Date
)
