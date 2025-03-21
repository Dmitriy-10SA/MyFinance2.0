package com.andef.myfinance.data.expense.mapper

internal object ExpenseToExpenseModelMapper {
    fun map(expense: com.andef.myfinance.domain.expense.entities.Expense): com.andef.myfinance.data.expense.model.ExpenseModel {
        return com.andef.myfinance.data.expense.model.ExpenseModel(
            id = expense.id,
            amount = expense.amount,
            category = expense.category,
            comment = expense.comment,
            date = expense.date.time
        )
    }
}