package com.andef.myfinance.data.database.expense.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.andef.myfinance.data.database.expense.datasource.ExpenseDao
import com.andef.myfinance.data.database.expense.mapper.ExpenseModelListToExpenseListMapper
import com.andef.myfinance.data.database.expense.mapper.ExpenseToExpenseModelMapper
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.domain.database.expense.repository.ExpenseRepository
import com.andef.myfinance.presentation.utils.toStartOfDay
import java.util.Date
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val dao: ExpenseDao,
    private val expenseToExpenseModelMapper: ExpenseToExpenseModelMapper,
    private val expenseModelListToExpenseListMapper: ExpenseModelListToExpenseListMapper
) : ExpenseRepository {
    override suspend fun addExpense(expense: Expense) {
        dao.addExpense(expenseToExpenseModelMapper.map(expense))
    }

    override suspend fun changeExpense(
        expense: Expense,
        newAmount: Double?,
        newCategory: ExpenseCategory?,
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

    override fun getExpenses(startDate: Date, endDate: Date): LiveData<List<Expense>> {
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
}