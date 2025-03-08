package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class GbpRub(
    amount: Double,
    iconResId: Int = R.drawable.gbp,
    nameResId: Int = R.string.gbp
) : CurrencyRub(amount, iconResId, nameResId)