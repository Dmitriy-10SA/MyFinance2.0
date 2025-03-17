package com.andef.myfinance.data.database.income.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.andef.myfinance.data.database.income.datasource.IncomeDao
import com.andef.myfinance.data.database.income.mapper.IncomeModelListToIncomeListMapper
import com.andef.myfinance.data.database.income.mapper.IncomeToIncomeModelMapper
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import com.andef.myfinance.presentation.utils.toStartOfDay
import java.util.Date
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val dao: IncomeDao,
    private val incomeToIncomeModelMapper: IncomeToIncomeModelMapper,
    private val incomeModelListToIncomeListMapper: IncomeModelListToIncomeListMapper
) : IncomeRepository {
    override suspend fun addIncome(income: Income) {
        dao.addIncome(incomeToIncomeModelMapper.map(income))
    }

    override suspend fun changeIncome(
        income: Income,
        newAmount: Double?,
        newCategory: IncomeCategory?,
        newComment: String?,
        newDate: Date?
    ) {
        dao.changeIncome(
            id = income.id,
            newAmount = newAmount ?: income.amount,
            newCategory = newCategory ?: income.category,
            newComment = newComment ?: income.comment,
            newDate = newDate?.toStartOfDay()?.time ?: income.date.toStartOfDay().time
        )
    }

    override suspend fun removeIncome(id: Int) {
        dao.removeIncome(id)
    }

    override fun getIncomes(startDate: Date, endDate: Date): LiveData<List<Income>> {
        return dao.getIncomes(startDate.toStartOfDay().time, endDate.toStartOfDay().time).map {
            incomeModelListToIncomeListMapper.map(it)
        }
    }

    override fun getFullAmount(startDate: Date, endDate: Date): LiveData<Double> {
        return dao.getFullAmount(startDate.toStartOfDay().time, endDate.toStartOfDay().time)
    }

    override suspend fun getIncomesAmount(incomes: List<Income>): List<Double> {
        var salaryAmount = 0.0
        var bankAmount = 0.0
        var luckAmount = 0.0
        var giftsAmount = 0.0
        var otherAmount = 0.0
        incomes.forEach { income ->
            when (income.category) {
                IncomeCategory.SALARY -> salaryAmount += income.amount
                IncomeCategory.BANK -> bankAmount += income.amount
                IncomeCategory.LUCK -> luckAmount += income.amount
                IncomeCategory.GIFTS -> giftsAmount += income.amount
                IncomeCategory.OTHER -> otherAmount += income.amount
            }
        }
        return listOf(salaryAmount, bankAmount, luckAmount, giftsAmount, otherAmount)
    }
}