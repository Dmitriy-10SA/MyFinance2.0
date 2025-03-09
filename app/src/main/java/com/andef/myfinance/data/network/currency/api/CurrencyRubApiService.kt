package com.andef.myfinance.data.network.currency.api

import com.andef.myfinance.data.network.currency.dto.aud.AudRubDto
import com.andef.myfinance.data.network.currency.dto.btc.BtcRubDto
import com.andef.myfinance.data.network.currency.dto.cad.CadRubDto
import com.andef.myfinance.data.network.currency.dto.chf.ChfRubDto
import com.andef.myfinance.data.network.currency.dto.cny.CnyRubDto
import com.andef.myfinance.data.network.currency.dto.eth.EthRubDto
import com.andef.myfinance.data.network.currency.dto.eur.EurRubDto
import com.andef.myfinance.data.network.currency.dto.gbp.GbpRubDto
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

    companion object {
        private const val AUD = "aud.json"
        private const val BTC = "btc.json"
        private const val CAD = "cad.json"
        private const val CHF = "chf.json"
        private const val CNY = "cny.json"
        private const val ETH = "eth.json"
        private const val EUR = "eur.json"
        private const val GBP = "gbp.json"
        private const val JPY = "jpy.json"
        private const val USD = "usd.json"
    }
}