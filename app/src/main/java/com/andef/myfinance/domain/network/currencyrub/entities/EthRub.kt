package com.andef.myfinance.domain.network.currencyrub.entities

import com.andef.myfinance.R

class EthRub(
    amount: Double,
    iconResId: Int = R.drawable.eth,
    nameResId: Int = R.string.eth
) : CurrencyRub(amount, iconResId, nameResId)