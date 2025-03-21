package com.andef.myfinance.domain.expense.entities

//категория расхода
sealed class ExpenseCategory {
    data object Products : ExpenseCategory()
    data object Cafe : ExpenseCategory()
    data object Home : ExpenseCategory()
    data object Gifts : ExpenseCategory()
    data object Study : ExpenseCategory()
    data object Health : ExpenseCategory()
    data object Transport : ExpenseCategory()
    data object Sport : ExpenseCategory()
    data object Clothes : ExpenseCategory()
    data object Other : ExpenseCategory()
}