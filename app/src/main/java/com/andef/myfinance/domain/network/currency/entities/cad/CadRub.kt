package com.andef.myfinance.domain.network.currency.entities.cad

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class CadRub(
    amount: Double,
    iconResId: Int = R.drawable.cad,
    nameResId: Int = R.string.cad
) : CurrencyRub(amount, iconResId, nameResId)