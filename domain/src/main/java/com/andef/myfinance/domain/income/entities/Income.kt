package com.andef.myfinance.domain.income.entities

import java.util.Date

//доход
data class Income(
    val id: Int = 0,
    val amount: Double,
    val category: IncomeCategory,
    val comment: String,
    val date: Date
)