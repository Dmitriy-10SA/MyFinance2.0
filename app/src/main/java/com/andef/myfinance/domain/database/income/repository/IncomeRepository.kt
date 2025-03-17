package com.andef.myfinance.domain.database.income.repository

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
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
    fun getIncomes(startDate: Date, endDate: Date): LiveData<List<Income>>
    fun getFullAmount(startDate: Date, endDate: Date): LiveData<Double>
    suspend fun getIncomesAmount(incomes: List<Income>): List<Double>
}