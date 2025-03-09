package com.andef.myfinance.domain.network.currency.entities.aud

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class AudRub(
    amount: Double,
    iconResId: Int = R.drawable.aud,
    nameResId: Int = R.string.aud
) : CurrencyRub(amount, iconResId, nameResId)