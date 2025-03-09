package com.andef.myfinance.domain.network.currency.entities.btc

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class BtcRub(
    amount: Double,
    iconResId: Int = R.drawable.btc,
    nameResId: Int = R.string.btc
) : CurrencyRub(amount, iconResId, nameResId)