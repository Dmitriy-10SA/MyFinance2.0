package com.andef.myfinance.data.network.currency.api

import com.andef.myfinance.data.network.currency.dto.aud.AudRubDto
import com.andef.myfinance.data.network.currency.dto.btc.BtcRubDto
import com.andef.myfinance.data.network.currency.dto.cad.CadRubDto
import com.andef.myfinance.data.network.currency.dto.chf.ChfRubDto
import com.andef.myfinance.data.network.currency.dto.cny.CnyRubDto
import com.andef.myfinance.data.network.currency.dto.eth.EthRubDto
import com.andef.myfinance.data.network.currency.dto.eur.EurRubDto
import com.andef.myfinance.data.network.currency.dto.gbp.GbpRubDto
import com.andef.myfinance.data.network.currency.dto.hkd.HkdRubDto
import com.andef.myfinance.data.network.currency.dto.jpy.JpyRubDto
import com.andef.myfinance.data.network.currency.dto.usd.UsdRubDto
import retrofit2.http.GET

interface CurrencyRubApiService {
    @GET(AUD)
    suspend fun getAudRub(): AudRubDto

    @GET(BTC)
    suspend fun getBtcRub(): BtcRubDto

    @GET(CAD)
    suspend fun getCadRub(): CadRubDto

    @GET(CHF)
    suspend fun getChfRub(): ChfRubDto

    @GET(CNY)
    suspend fun getCnyRub(): CnyRubDto

    @GET(ETH)
    suspend fun getEthRub(): EthRubDto

    @GET(EUR)
    suspend fun getEurRub(): EurRubDto

    @GET(GBP)
    suspend fun getGbpRub(): GbpRubDto

    @GET(JPY)
    suspend fun getJpyRub(): JpyRubDto

    @GET(USD)
    suspend fun getUsdRub(): UsdRubDto

    @GET(HKD)
    suspend fun getHkdRub(): HkdRubDto

    companion object {
        private const val AUD = "currency-api@latest/v1/currencies/aud.json"
        private const val BTC = "currency-api@latest/v1/currencies/btc.json"
        private const val CAD = "currency-api@latest/v1/currencies/cad.json"
        private const val CHF = "currency-api@latest/v1/currencies/chf.json"
        private const val CNY = "currency-api@latest/v1/currencies/cny.json"
        private const val ETH = "currency-api@latest/v1/currencies/eth.json"
        private const val EUR = "currency-api@latest/v1/currencies/eur.json"
        private const val GBP = "currency-api@latest/v1/currencies/gbp.json"
        private const val JPY = "currency-api@latest/v1/currencies/jpy.json"
        private const val USD = "currency-api@latest/v1/currencies/usd.json"
        private const val HKD = "currency-api@latest/v1/currencies/hkd.json"
    }
}