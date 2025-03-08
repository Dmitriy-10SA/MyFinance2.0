package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class CnyRub(
    amount: Double,
    iconResId: Int = R.drawable.cny,
    nameResId: Int = R.string.cny
) : CurrencyRub(amount, iconResId, nameResId)