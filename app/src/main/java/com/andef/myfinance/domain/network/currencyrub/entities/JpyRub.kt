package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class JpyRub(
    amount: Double,
    iconResId: Int = R.drawable.jpy,
    nameResId: Int = R.string.jpy
) : CurrencyRub(amount, iconResId, nameResId)