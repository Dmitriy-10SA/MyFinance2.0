package com.andef.myfinance.data.expense.repository

import com.andef.myfinance.data.expense.datasource.ExpenseDao
import com.andef.myfinance.data.expense.mapper.ExpenseModelListToExpenseListMapper
import com.andef.myfinance.data.expense.mapper.ExpenseModelToExpenseMapper
import com.andef.myfinance.data.expense.mapper.ExpenseToExpenseModelMapper
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao,
) : com.andef.myfinance.domain.expense.repository.ExpenseRepository {
    override suspend fun addExpense(expense: Expense) {
        dao.addExpense(ExpenseToExpenseModelMapper.map(expense))
    }

    override suspend fun changeExpense(
        id: Int,
        amount: Double,
        category: ExpenseCategory,
        comment: String,
        date: Date
    ) {
        dao.changeExpense(id, amount, category, comment, date.toStartOfDay().time)
    }

    override suspend fun removeExpense(id: Int) {
        dao.removeExpense(id)
    }

    override suspend fun getExpense(id: Int): Expense {
        return ExpenseModelToExpenseMapper.map(dao.getExpense(id))
    }

    override fun getExpenseList(startDate: Date, endDate: Date): Flow<List<Expense>> {
        return dao.getExpenseList(startDate.toStartOfDay().time, endDate.toStartOfDay().time).map {
            ExpenseModelListToExpenseListMapper.map(it)
        }
    }

    override fun getFullExpenseAmount(startDate: Date, endDate: Date): Flow<Double> {
        return dao.getFullExpenseAmount(startDate.toStartOfDay().time, endDate.toStartOfDay().time)
    }

    override suspend fun getAmountForEachTypeExpense(expenses: List<Expense>): List<Double> {
        var productsAmount = 0.0
        var cafeAmount = 0.0
        var homeAmount = 0.0
        var giftsAmount = 0.0
        var studyAmount = 0.0
        var healthAmount = 0.0
        var transportAmount = 0.0
        var sportAmount = 0.0
        var clothesAmount = 0.0
        var otherAmount = 0.0
        for (expense in expenses) {
            when (expense.category) {
                ExpenseCategory.Products -> productsAmount += expense.amount
                ExpenseCategory.Cafe -> cafeAmount += expense.amount
                ExpenseCategory.Home -> homeAmount += expense.amount
                ExpenseCategory.Gifts -> giftsAmount += expense.amount
                ExpenseCategory.Study -> studyAmount += expense.amount
                ExpenseCategory.Health -> healthAmount += expense.amount
                ExpenseCategory.Transport -> transportAmount += expense.amount
                ExpenseCategory.Sport -> sportAmount += expense.amount
                ExpenseCategory.Clothes -> clothesAmount += expense.amount
                ExpenseCategory.Other -> otherAmount += expense.amount
            }
        }
        return listOf(
            productsAmount,
            cafeAmount,
            homeAmount,
            giftsAmount,
            studyAmount,
            healthAmount,
            transportAmount,
            sportAmount,
            clothesAmount,
            otherAmount
        )
    }
}