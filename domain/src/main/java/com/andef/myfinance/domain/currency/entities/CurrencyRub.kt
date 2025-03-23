package com.andef.myfinance.domain.currency.entities

sealed class CurrencyRub(amount: Double) {
    data class Aud(val id: Int = 1, val amount: Double) : CurrencyRub(amount = amount)
    data class Btc(val id: Int = 2, val amount: Double) : CurrencyRub(amount = amount)
    data class Cad(val id: Int = 3, val amount: Double) : CurrencyRub(amount = amount)
    data class Chf(val id: Int = 4, val amount: Double) : CurrencyRub(amount = amount)
    data class Cny(val id: Int = 5, val amount: Double) : CurrencyRub(amount = amount)
    data class Eth(val id: Int = 6, val amount: Double) : CurrencyRub(amount = amount)
    data class Eur(val id: Int = 7, val amount: Double) : CurrencyRub(amount = amount)
    data class Gbp(val id: Int = 8, val amount: Double) : CurrencyRub(amount = amount)
    data class Hkd(val id: Int = 9, val amount: Double) : CurrencyRub(amount = amount)
    data class Jpy(val id: Int = 10, val amount: Double) : CurrencyRub(amount = amount)
    data class Usd(val id: Int = 11, val amount: Double) : CurrencyRub(amount = amount)
}