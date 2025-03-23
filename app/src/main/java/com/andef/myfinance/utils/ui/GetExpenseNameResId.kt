package com.andef.myfinance.utils.ui

import com.andef.myfinance.R
import com.andef.myfinance.domain.expense.entities.ExpenseCategory

fun getExpenseNameResId(expenseCategory: ExpenseCategory): Int {
    return when (expenseCategory) {
        ExpenseCategory.PRODUCTS -> R.string.products
        ExpenseCategory.CAFE -> R.string.cafe
        ExpenseCategory.HOME -> R.string.home
        ExpenseCategory.GIFTS -> R.string.gifts
        ExpenseCategory.STUDY -> R.string.study
        ExpenseCategory.HEALTH -> R.string.health
        ExpenseCategory.TRANSPORT -> R.string.transport
        ExpenseCategory.SPORT -> R.string.sport
        ExpenseCategory.CLOTHES -> R.string.clothes
        ExpenseCategory.OTHER -> R.string.other
    }
}