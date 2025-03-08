package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class CadRub(
    amount: Double,
    iconResId: Int = R.drawable.cad,
    nameResId: Int = R.string.cad
) : CurrencyRub(amount, iconResId, nameResId)