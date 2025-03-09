package com.andef.myfinance.domain.network.currency.entities.eur

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class EurRub(
    amount: Double,
    iconResId: Int = R.drawable.eur,
    nameResId: Int = R.string.eur
) : CurrencyRub(amount, iconResId, nameResId)