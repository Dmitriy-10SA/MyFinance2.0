package com.andef.myfinance.domain.network.currency.entities.usd

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class UsdRub(
    amount: Double,
    iconResId: Int = R.drawable.usd,
    nameResId: Int = R.string.usd
) : CurrencyRub(amount, iconResId, nameResId)