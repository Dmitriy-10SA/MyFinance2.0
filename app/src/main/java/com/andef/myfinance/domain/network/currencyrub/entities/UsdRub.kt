package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class UsdRub(
    amount: Double,
    iconResId: Int = R.drawable.usd,
    nameResId: Int = R.string.usd
) : CurrencyRub(amount, iconResId, nameResId)