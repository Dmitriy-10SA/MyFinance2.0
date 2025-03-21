package com.andef.myfinance.data.database.income.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.andef.myfinance.data.database.income.datasource.IncomeDao
import com.andef.myfinance.data.database.income.mapper.IncomeModelListToIncomeListMapper
import com.andef.myfinance.data.database.income.mapper.IncomeToIncomeModelMapper
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.domain.income.repository.IncomeRepository
import com.andef.myfinance.presentation.utils.toStartOfDay
import java.util.Date
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val dao: IncomeDao,
    private val incomeToIncomeModelMapper: IncomeToIncomeModelMapper,
    private val incomeModelListToIncomeListMapper: IncomeModelListToIncomeListMapper
) : com.andef.myfinance.domain.income.repository.IncomeRepository {
    override suspend fun addIncome(income: com.andef.myfinance.domain.income.entities.Income) {
        dao.addIncome(incomeToIncomeModelMapper.map(income))
    }

    override suspend fun changeIncome(
        income: com.andef.myfinance.domain.income.entities.Income,
        newAmount: Double?,
        newCategory: com.andef.myfinance.domain.income.entities.IncomeCategory?,
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

    override fun getIncomes(startDate: Date, endDate: Date): LiveData<List<com.andef.myfinance.domain.income.entities.Income>> {
        return dao.getIncomes(startDate.toStartOfDay().time, endDate.toStartOfDay().time).map {
            incomeModelListToIncomeListMapper.map(it)
        }
    }

    override fun getFullAmount(startDate: Date, endDate: Date): LiveData<Double> {
        return dao.getFullAmount(startDate.toStartOfDay().time, endDate.toStartOfDay().time)
    }

    override suspend fun getIncomesAmount(incomes: List<com.andef.myfinance.domain.income.entities.Income>): List<Double> {
        var salaryAmount = 0.0
        var bankAmount = 0.0
        var luckAmount = 0.0
        var giftsAmount = 0.0
        var otherAmount = 0.0
        incomes.forEach { income ->
            when (income.category) {
                com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY -> salaryAmount += income.amount
                com.andef.myfinance.domain.income.entities.IncomeCategory.BANK -> bankAmount += income.amount
                com.andef.myfinance.domain.income.entities.IncomeCategory.LUCK -> luckAmount += income.amount
                com.andef.myfinance.domain.income.entities.IncomeCategory.GIFTS -> giftsAmount += income.amount
                com.andef.myfinance.domain.income.entities.IncomeCategory.OTHER -> otherAmount += income.amount
            }
        }
        return listOf(salaryAmount, bankAmount, luckAmount, giftsAmount, otherAmount)
    }
}