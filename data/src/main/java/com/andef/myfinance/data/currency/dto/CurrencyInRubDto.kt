package com.andef.myfinance.data.currency.dto

import com.google.gson.annotations.SerializedName

sealed class CurrencyInRubDto(amount: Double) {
    data class AudInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class BtcInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class CadInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class ChfInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class CnyInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class EthInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class EurInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class GbpInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class HkdInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class JpyInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)
    data class UsdInRubDto(@SerializedName(RUB) val amount: Double) : CurrencyInRubDto(amount)

    companion object {
        private const val RUB = "rub"
    }
}