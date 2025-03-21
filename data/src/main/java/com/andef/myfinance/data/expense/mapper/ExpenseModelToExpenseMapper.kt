package com.andef.myfinance.data.expense.mapper

import com.andef.myfinance.data.expense.model.ExpenseModel
import com.andef.myfinance.domain.expense.entities.Expense
import java.util.Date

internal object ExpenseModelToExpenseMapper {
    fun map(expenseModel: ExpenseModel): Expense {
        return Expense(
            id = expenseModel.id,
            amount = expenseModel.amount,
            category = expenseModel.category,
            comment = expenseModel.comment,
            date = Date(expenseModel.date)
        )
    }
}