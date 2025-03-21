package com.andef.myfinance.data.database.expense.mapper

import com.andef.myfinance.data.database.expense.model.ExpenseModel
import com.andef.myfinance.domain.expense.entities.Expense
import java.util.Date
import javax.inject.Inject

class ExpenseModelListToExpenseListMapper @Inject constructor() {
    private fun map(expenseModel: ExpenseModel): com.andef.myfinance.domain.expense.entities.Expense {
        return com.andef.myfinance.domain.expense.entities.Expense(
            id = expenseModel.id,
            amount = expenseModel.amount,
            category = expenseModel.category,
            comment = expenseModel.comment,
            date = Date(expenseModel.date)
        )
    }

    fun map(expenseModelList: List<ExpenseModel>): List<com.andef.myfinance.domain.expense.entities.Expense> {
        return expenseModelList.map {
            this.map(it)
        }
    }
}