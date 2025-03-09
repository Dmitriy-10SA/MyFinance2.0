package com.andef.myfinance.domain.network.currency.entities.eth

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class EthRub(
    amount: Double,
    iconResId: Int = R.drawable.eth,
    nameResId: Int = R.string.eth
) : CurrencyRub(amount, iconResId, nameResId)