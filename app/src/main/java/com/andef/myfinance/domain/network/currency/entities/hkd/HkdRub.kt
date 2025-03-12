package com.andef.myfinance.domain.network.currency.entities.hkd

import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub

class HkdRub(
    amount: Double,
    iconResId: Int = R.drawable.hkd ,
    nameResId: Int = R.string.hkd
) : CurrencyRub(amount, iconResId, nameResId)