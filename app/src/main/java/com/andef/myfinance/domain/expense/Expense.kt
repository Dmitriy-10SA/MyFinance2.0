package com.andef.myfinance.domain.expense

import java.util.Date

data class Expense(
    val id: Int,
    val amount: Double,
    val category: ExpenseCategory,
    val comment: String,
    //дата в формате timestamp
    val date: Date
)
