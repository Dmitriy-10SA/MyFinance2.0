package com.andef.myfinance.domain.network.currency.entities.gbp

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class GbpRub(
    amount: Double,
    iconResId: Int = R.drawable.gbp,
    nameResId: Int = R.string.gbp
) : CurrencyRub(amount, iconResId, nameResId)