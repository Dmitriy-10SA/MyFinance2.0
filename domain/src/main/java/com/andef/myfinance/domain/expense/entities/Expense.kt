package com.andef.myfinance.domain.expense.entities

import java.util.Date

//расход
data class Expense(
    val id: Int = 0,
    val amount: Double,
    val category: ExpenseCategory,
    val comment: String,
    val date: Date
)
