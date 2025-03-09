package com.andef.myfinance.domain.database.expense.entities

import com.andef.myfinance.R

//тип расхода
enum class ExpenseCategory(
    //иконка для типа расхода (ресурс)
    val iconResId: Int,
    //имя для типа расхода (ресурс)
    val nameResId: Int
) {
    PRODUCTS(
        nameResId = R.string.products,
        iconResId = R.drawable.products
    ),
    CAFE(
        nameResId = R.string.cafe,
        iconResId = R.drawable.cafe
    ),
    HOME(
        nameResId = R.string.home,
        iconResId = R.drawable.home
    ),
    GIFTS(
        nameResId = R.string.gifts,
        iconResId = R.drawable.gifts
    ),
    STUDY(
        nameResId = R.string.study,
        iconResId = R.drawable.study
    ),
    HEALTH(
        nameResId = R.string.health,
        iconResId = R.drawable.health
    ),
    TRANSPORT(
        nameResId = R.string.transport,
        iconResId = R.drawable.transport
    ),
    SPORT(
        nameResId = R.string.sport,
        iconResId = R.drawable.sport
    ),
    CLOTHES(
        nameResId = R.string.clothes,
        iconResId = R.drawable.clothes
    ),
    OTHER(
        nameResId = R.string.other,
        iconResId = R.drawable.other
    )
}