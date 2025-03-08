package com.andef.myfinance.domain.income

import java.util.Date

data class Income(
    val id: Long,
    val amount: Double,
    val category: IncomeCategory,
    val comment: String,
    val date: Date
)