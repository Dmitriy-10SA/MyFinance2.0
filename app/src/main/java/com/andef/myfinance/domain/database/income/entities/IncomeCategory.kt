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
        nameResId = R.string.salary,
        iconResId = R.drawable.salary
    ),
    BANK(
        nameResId = R.string.bank,
        iconResId = R.drawable.bank
    ),
    LUCK(
        nameResId = R.string.luck,
        iconResId = R.drawable.luck
    ),
    GIFTS(
        nameResId = R.string.gifts,
        iconResId = R.drawable.gifts
    ),
    OTHER(
        nameResId = R.string.other,
        iconResId = R.drawable.other
    )
}