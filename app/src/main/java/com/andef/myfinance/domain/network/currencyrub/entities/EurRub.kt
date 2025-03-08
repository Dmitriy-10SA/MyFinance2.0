package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class EurRub(
    amount: Double,
    iconResId: Int = R.drawable.eur,
    nameResId: Int = R.string.eur
) : CurrencyRub(amount, iconResId, nameResId)