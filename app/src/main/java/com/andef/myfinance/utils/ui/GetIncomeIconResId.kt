package com.andef.myfinance.utils.ui

import com.andef.myfinance.R
import com.andef.myfinance.domain.income.entities.IncomeCategory

fun getIncomeIconResId(incomeCategory: IncomeCategory): Int {
    return when(incomeCategory) {
        IncomeCategory.SALARY -> R.drawable.salary
        IncomeCategory.BANK -> R.drawable.bank
        IncomeCategory.LUCK -> R.drawable.luck
        IncomeCategory.GIFTS -> R.drawable.gifts
        IncomeCategory.OTHER -> R.drawable.other
    }
}