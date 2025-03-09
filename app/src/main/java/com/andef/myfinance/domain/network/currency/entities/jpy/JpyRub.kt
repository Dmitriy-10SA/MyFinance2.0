package com.andef.myfinance.domain.network.currency.entities.jpy

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class JpyRub(
    amount: Double,
    iconResId: Int = R.drawable.jpy,
    nameResId: Int = R.string.jpy
) : CurrencyRub(amount, iconResId, nameResId)