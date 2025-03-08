package com.andef.myfinance.domain.database.income.entities

import com.andef.myfinance.R

//тип дохода
enum class IncomeCategory(
    //иконка для типа дохода (ресурс)
    val iconResId: Int,
    //имя для типа дохода (ресурс)
    val nameResId: Int
) {
    SALARY(
        iconResId = R.string.salary,
        nameResId = R.drawable.salary
    ),
    BANK(
        iconResId = R.string.bank,
        nameResId = R.drawable.bank
    ),
    LUCK(
        iconResId = R.string.luck,
        nameResId = R.drawable.luck
    ),
    GIFTS(
        iconResId = R.string.gifts,
        nameResId = R.drawable.gifts
    ),
    OTHER(
        iconResId = R.string.other,
        nameResId = R.drawable.other
    )
}