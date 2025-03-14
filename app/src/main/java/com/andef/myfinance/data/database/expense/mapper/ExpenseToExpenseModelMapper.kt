package com.andef.myfinance.data.database.expense.mapper

import com.andef.myfinance.data.database.expense.model.ExpenseModel
import com.andef.myfinance.domain.database.expense.entities.Expense
import javax.inject.Inject

class ExpenseToExpenseModelMapper @Inject constructor() {
    fun map(expense: Expense): ExpenseModel {
        return ExpenseModel(
            id = expense.id,
            amount = expense.amount,
            category = expense.category,
            comment = expense.comment,
            date = expense.date.time
        )
    }
}