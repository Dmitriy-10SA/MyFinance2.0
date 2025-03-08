package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class ChfRub(
    amount: Double,
    iconResId: Int = R.drawable.chf,
    nameResId: Int = R.string.chf
) : CurrencyRub(amount, iconResId, nameResId)