package com.andef.myfinance.utils.ui

import com.andef.myfinance.R
import com.andef.myfinance.domain.expense.entities.ExpenseCategory

fun getExpenseIconResId(expenseCategory: ExpenseCategory): Int {
    return when (expenseCategory) {
        ExpenseCategory.PRODUCTS -> R.drawable.products
        ExpenseCategory.CAFE -> R.drawable.cafe
        ExpenseCategory.HOME -> R.drawable.home
        ExpenseCategory.GIFTS -> R.drawable.gifts
        ExpenseCategory.STUDY -> R.drawable.study
        ExpenseCategory.HEALTH -> R.drawable.health
        ExpenseCategory.TRANSPORT -> R.drawable.transport
        ExpenseCategory.SPORT -> R.drawable.sport
        ExpenseCategory.CLOTHES -> R.drawable.clothes
        ExpenseCategory.OTHER -> R.drawable.other
    }
}