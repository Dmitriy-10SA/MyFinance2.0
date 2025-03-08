package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class AudRub(
    amount: Double,
    iconResId: Int = R.drawable.aud,
    nameResId: Int = R.string.aud
) : CurrencyRub(amount, iconResId, nameResId)