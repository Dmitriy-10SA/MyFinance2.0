package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class BtcRub(
    amount: Double,
    iconResId: Int = R.drawable.btc,
    nameResId: Int = R.string.btc
) : CurrencyRub(amount, iconResId, nameResId)