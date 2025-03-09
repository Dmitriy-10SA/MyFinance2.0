package com.andef.myfinance.domain.network.currency.entities.cny

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class CnyRub(
    amount: Double,
    iconResId: Int = R.drawable.cny,
    nameResId: Int = R.string.cny
) : CurrencyRub(amount, iconResId, nameResId)