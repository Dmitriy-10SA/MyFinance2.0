package com.andef.myfinance.data.currency.dto

import com.google.gson.annotations.SerializedName

sealed class CurrencyRubDto(currencyInRubDto: CurrencyInRubDto) {
    data class AudRubDto(@SerializedName(AUD) val audInRubDto: CurrencyInRubDto.AudInRubDto) :
        CurrencyRubDto(audInRubDto)

    data class BtcRubDto(@SerializedName(BTC) val btcInRubDto: CurrencyInRubDto.BtcInRubDto) :
        CurrencyRubDto(btcInRubDto)

    data class CadRubDto(@SerializedName(CAD) val cadInRubDto: CurrencyInRubDto.CadInRubDto) :
        CurrencyRubDto(cadInRubDto)

    data class ChfRubDto(@SerializedName(CHF) val chfInRubDto: CurrencyInRubDto.ChfInRubDto) :
        CurrencyRubDto(chfInRubDto)

    data class CnyRubDto(@SerializedName(CNY) val cnyInRubDto: CurrencyInRubDto.CnyInRubDto) :
        CurrencyRubDto(cnyInRubDto)

    data class EthRubDto(@SerializedName(ETH) val ethInRubDto: CurrencyInRubDto.EthInRubDto) :
        CurrencyRubDto(ethInRubDto)

    data class EurRubDto(@SerializedName(EUR) val eurInRubDto: CurrencyInRubDto.EurInRubDto) :
        CurrencyRubDto(eurInRubDto)

    data class GbpRubDto(@SerializedName(GBP) val gbpInRubDto: CurrencyInRubDto.GbpInRubDto) :
        CurrencyRubDto(gbpInRubDto)

    data class HkdRubDto(@SerializedName(HKD) val hkdInRubDto: CurrencyInRubDto.HkdInRubDto) :
        CurrencyRubDto(hkdInRubDto)

    data class JpyRubDto(@SerializedName(JPY) val jpyInRubDto: CurrencyInRubDto.JpyInRubDto) :
        CurrencyRubDto(jpyInRubDto)

    data class UsdRubDto(@SerializedName(USD) val usdInRubDto: CurrencyInRubDto.UsdInRubDto) :
        CurrencyRubDto(usdInRubDto)

    companion object {
        private const val AUD = "aud"
        private const val BTC = "btc"
        private const val CAD = "cad"
        private const val CHF = "chf"
        private const val CNY = "cny"
        private const val ETH = "eth"
        private const val EUR = "eur"
        private const val GBP = "gbp"
        private const val JPY = "jpy"
        private const val USD = "usd"
        private const val HKD = "hkd"
    }
}