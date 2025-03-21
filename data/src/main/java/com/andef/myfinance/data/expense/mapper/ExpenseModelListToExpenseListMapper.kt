package com.andef.myfinance.data.expense.mapper

internal object ExpenseModelListToExpenseListMapper {
    fun map(expenseModelList: List<com.andef.myfinance.data.expense.model.ExpenseModel>): List<com.andef.myfinance.domain.expense.entities.Expense> {
        return expenseModelList.map {
            ExpenseModelToExpenseMapper.map(it)
        }
    }
}