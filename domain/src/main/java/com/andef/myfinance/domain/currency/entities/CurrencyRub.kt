package com.andef.myfinance.domain.currency.entities

sealed class CurrencyRub(amount: Double) {
    data class Aud(val amount: Double) : CurrencyRub(amount = amount)
    data class Btc(val amount: Double) : CurrencyRub(amount = amount)
    data class Cad(val amount: Double) : CurrencyRub(amount = amount)
    data class Chf(val amount: Double) : CurrencyRub(amount = amount)
    data class Cny(val amount: Double) : CurrencyRub(amount = amount)
    data class Eth(val amount: Double) : CurrencyRub(amount = amount)
    data class Eur(val amount: Double) : CurrencyRub(amount = amount)
    data class Gbp(val amount: Double) : CurrencyRub(amount = amount)
    data class Hkd(val amount: Double) : CurrencyRub(amount = amount)
    data class Jpy(val amount: Double) : CurrencyRub(amount = amount)
    data class Usd(val amount: Double) : CurrencyRub(amount = amount)
}