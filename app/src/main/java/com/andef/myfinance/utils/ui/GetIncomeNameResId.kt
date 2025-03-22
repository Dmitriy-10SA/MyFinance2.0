package com.andef.myfinance.utils.ui

import com.andef.myfinance.R
import com.andef.myfinance.domain.income.entities.IncomeCategory

fun getIncomeNameResId(incomeCategory: IncomeCategory): Int {
    return when(incomeCategory) {
        IncomeCategory.SALARY -> R.string.salary
        IncomeCategory.BANK -> R.string.bank
        IncomeCategory.LUCK -> R.string.luck
        IncomeCategory.GIFTS -> R.string.gifts
        IncomeCategory.OTHER -> R.string.other
    }
}