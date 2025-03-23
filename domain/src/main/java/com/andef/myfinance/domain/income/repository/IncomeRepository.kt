package com.andef.myfinance.domain.income.repository

import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface IncomeRepository {
    suspend fun addIncome(income: Income)

    suspend fun changeIncome(
        id: Int,
        amount: Double,
        category: IncomeCategory,
        comment: String,
        date: Date
    )

    suspend fun removeIncome(id: Int)

    suspend fun getIncome(id: Int): Income

    fun getIncomeList(startDate: Date, endDate: Date): Flow<List<Income>>

    fun getFullIncomeAmount(startDate: Date, endDate: Date): Flow<Double>

    suspend fun getAmountForEachTypeIncome(incomes: List<Income>): List<Double>
}