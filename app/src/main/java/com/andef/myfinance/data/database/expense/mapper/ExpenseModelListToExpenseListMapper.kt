package com.andef.myfinance.data.database.expense.mapper

import com.andef.myfinance.data.database.expense.model.ExpenseModel
import com.andef.myfinance.domain.database.expense.entities.Expense
import java.util.Date
import javax.inject.Inject

class ExpenseModelListToExpenseListMapper @Inject constructor() {
    private fun map(expenseModel: ExpenseModel): Expense {
        return Expense(
            id = expenseModel.id,
            amount = expenseModel.amount,
            category = expenseModel.category,
            comment = expenseModel.comment,
            date = Date(expenseModel.date)
        )
    }

    fun map(expenseModelList: List<ExpenseModel>): List<Expense> {
        return expenseModelList.map {
            this.map(it)
        }
    }
}