package com.andef.myfinance.data.database.expense.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.andef.myfinance.data.database.expense.datasource.ExpenseDao
import com.andef.myfinance.data.database.expense.mapper.ExpenseModelListToExpenseListMapper
import com.andef.myfinance.data.database.expense.mapper.ExpenseToExpenseModelMapper
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.expense.repository.ExpenseRepository
import com.andef.myfinance.presentation.utils.toStartOfDay
import java.util.Date
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao,
    private val expenseToExpenseModelMapper: ExpenseToExpenseModelMapper,
    private val expenseModelListToExpenseListMapper: ExpenseModelListToExpenseListMapper
) : com.andef.myfinance.domain.expense.repository.ExpenseRepository {
    override suspend fun addExpense(expense: com.andef.myfinance.domain.expense.entities.Expense) {
        dao.addExpense(expenseToExpenseModelMapper.map(expense))
    }

    override suspend fun changeExpense(
        expense: com.andef.myfinance.domain.expense.entities.Expense,
        newAmount: Double?,
        newCategory: com.andef.myfinance.domain.expense.entities.ExpenseCategory?,
        newComment: String?,
        newDate: Date?
    ) {
        dao.changeExpense(
            id = expense.id,
            newAmount = newAmount ?: expense.amount,
            newCategory = newCategory ?: expense.category,
            newComment = newComment ?: expense.comment,
            newDate = newDate?.toStartOfDay()?.time ?: expense.date.toStartOfDay().time
        )
    }

    override suspend fun removeExpense(id: Int) {
        dao.removeExpense(id)
    }

    override fun getExpenses(startDate: Date, endDate: Date): LiveData<List<com.andef.myfinance.domain.expense.entities.Expense>> {
        return dao.getExpense(startDate.toStartOfDay().time, endDate.toStartOfDay().time).map {
            expenseModelListToExpenseListMapper.map(it)
        }
    }

    override fun getFullAmount(startDate: Date, endDate: Date): LiveData<Double> {
        return dao.getFullAmount(
            startDate.toStartOfDay().toStartOfDay().time,
            endDate.toStartOfDay().toStartOfDay().time
        )
    }

    override suspend fun getExpensesAmount(expenses: List<com.andef.myfinance.domain.expense.entities.Expense>): List<Double> {
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
        expenses.forEach { expense ->
            when (expense.category) {
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.PRODUCTS -> productsAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.CAFE -> cafeAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.HOME -> homeAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.GIFTS -> giftsAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.STUDY -> studyAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.HEALTH -> healthAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.TRANSPORT -> transportAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.SPORT -> sportAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.CLOTHES -> clothesAmount += expense.amount
                com.andef.myfinance.domain.expense.entities.ExpenseCategory.OTHER -> otherAmount += expense.amount
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