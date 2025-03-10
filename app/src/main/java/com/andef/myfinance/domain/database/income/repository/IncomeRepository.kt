package com.andef.myfinance.domain.database.income.repository

import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import kotlinx.coroutines.flow.Flow
import java.util.Date

interface IncomeRepository {
    suspend fun addIncome(income: Income)
    suspend fun changeIncome(
        income: Income,
        newAmount: Double? = null,
        newCategory: IncomeCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    )

    suspend fun removeIncome(id: Int)
    fun getIncomes(startDate: Date, endDate: Date): Flow<List<Income>>
    fun getFullAmount(startDate: Date, endDate: Date): Flow<Double>
}