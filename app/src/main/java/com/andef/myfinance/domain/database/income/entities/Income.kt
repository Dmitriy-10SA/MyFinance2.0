package com.andef.myfinance.domain.database.income.entities

import java.util.Date

data class Income(
    val id: Int = 0,
    val amount: Double,
    val category: IncomeCategory,
    val comment: String,
    val date: Date
)