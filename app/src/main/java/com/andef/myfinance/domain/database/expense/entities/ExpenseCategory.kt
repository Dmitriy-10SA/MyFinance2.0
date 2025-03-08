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
        iconResId = R.string.products,
        nameResId = R.drawable.products
    ),
    CAFE(
        iconResId = R.string.cafe,
        nameResId = R.drawable.cafe
    ),
    HOME(
        iconResId = R.string.home,
        nameResId = R.drawable.home
    ),
    GIFTS(
        iconResId = R.string.gifts,
        nameResId = R.drawable.gifts
    ),
    STUDY(
        iconResId = R.string.study,
        nameResId = R.drawable.study
    ),
    HEALTH(
        iconResId = R.string.health,
        nameResId = R.drawable.health
    ),
    TRANSPORT(
        iconResId = R.string.transport,
        nameResId = R.drawable.transport
    ),
    SPORT(
        iconResId = R.string.sport,
        nameResId = R.drawable.sport
    ),
    CLOTHES(
        iconResId = R.string.clothes,
        nameResId = R.drawable.clothes
    ),
    OTHER(
        iconResId = R.string.other,
        nameResId = R.drawable.other
    )
}