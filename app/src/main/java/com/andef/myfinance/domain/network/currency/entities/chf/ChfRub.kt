package com.andef.myfinance.domain.network.currency.entities.chf

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class ChfRub(
    amount: Double,
    iconResId: Int = R.drawable.chf,
    nameResId: Int = R.string.chf
) : CurrencyRub(amount, iconResId, nameResId)