package com.andef.myfinance.data.database.income.repository

import com.andef.myfinance.data.database.income.datasource.IncomeDao
import com.andef.myfinance.data.database.income.mapper.IncomeModelListToIncomeListMapper
import com.andef.myfinance.data.database.income.mapper.IncomeToIncomeModelMapper
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            newDate = newDate?.time ?: income.date.time
        )
    }

    override suspend fun removeIncome(id: Int) {
        dao.removeIncome(id)
    }

    override fun getIncomes(startDate: Date, endDate: Date): Flow<List<Income>> {
        return dao.getIncomes(startDate.time, endDate.time).map {
            incomeModelListToIncomeListMapper.map(it)
        }
    }

    override fun getFullAmount(startDate: Date, endDate: Date): Flow<Double> {
        return dao.getFullAmount(startDate.time, endDate.time)
    }
}