package com.andef.myfinance.data.income.repository

import com.andef.myfinance.data.income.datasource.IncomeDao
import com.andef.myfinance.data.income.mapper.IncomeModelListToIncomeListMapper
import com.andef.myfinance.data.income.mapper.IncomeModelToIncomeMapper
import com.andef.myfinance.data.income.mapper.IncomeToIncomeModelMapper
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.domain.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val dao: IncomeDao
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        dao.addIncome(IncomeToIncomeModelMapper.map(income))
    }

    override suspend fun changeIncome(
        id: Int,
        amount: Double,
        category: IncomeCategory,
        comment: String,
        date: Date
    ) {
        dao.changeIncome(id, amount, category, comment, date.toStartOfDay().time)
    }

    override suspend fun removeIncome(id: Int) {
        dao.removeIncome(id)
    }

    override suspend fun getIncome(id: Int): Income {
        return IncomeModelToIncomeMapper.map(dao.getIncome(id))
    }

    override fun getIncomeList(startDate: Date, endDate: Date): Flow<List<Income>> {
        return dao.getIncomeList(startDate.toStartOfDay().time, endDate.toStartOfDay().time).map {
            IncomeModelListToIncomeListMapper.map(it)
        }
    }

    override fun getFullIncomeAmount(startDate: Date, endDate: Date): Flow<Double> {
        return dao.getFullIncomeAmount(startDate.toStartOfDay().time, endDate.toStartOfDay().time)
    }

    override suspend fun getAmountForEachTypeIncome(incomes: List<Income>): List<Double> {
        var salaryAmount = 0.0
        var bankAmount = 0.0
        var luckAmount = 0.0
        var giftsAmount = 0.0
        var otherAmount = 0.0
        for (income in incomes) {
            when (income.category) {
                IncomeCategory.Salary -> salaryAmount += income.amount
                IncomeCategory.Bank -> bankAmount += income.amount
                IncomeCategory.Luck -> luckAmount += income.amount
                IncomeCategory.Gifts -> giftsAmount += income.amount
                IncomeCategory.Other -> otherAmount += income.amount
            }
        }
        return listOf(salaryAmount, bankAmount, luckAmount, giftsAmount, otherAmount)
    }
}