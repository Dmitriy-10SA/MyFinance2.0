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
import retrofit2.http.Path

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

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$AUD_SHORT_NAME_FOR_DATE")
    suspend fun getAudRub(@Path("formatDate") formatDate: String): AudRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$BTC_SHORT_NAME_FOR_DATE")
    suspend fun getBtcRub(@Path("formatDate") formatDate: String): BtcRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CAD_SHORT_NAME_FOR_DATE")
    suspend fun getCadRub(@Path("formatDate") formatDate: String): CadRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CHF_SHORT_NAME_FOR_DATE")
    suspend fun getChfRub(@Path("formatDate") formatDate: String): ChfRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CNY_SHORT_NAME_FOR_DATE")
    suspend fun getCnyRub(@Path("formatDate") formatDate: String): CnyRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$ETH_SHORT_NAME_FOR_DATE")
    suspend fun getEthRub(@Path("formatDate") formatDate: String): EthRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$EUR_SHORT_NAME_FOR_DATE")
    suspend fun getEurRub(@Path("formatDate") formatDate: String): EurRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$GBP_SHORT_NAME_FOR_DATE")
    suspend fun getGbpRub(@Path("formatDate") formatDate: String): GbpRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$JPY_SHORT_NAME_FOR_DATE")
    suspend fun getJpyRub(@Path("formatDate") formatDate: String): JpyRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$USD_SHORT_NAME_FOR_DATE")
    suspend fun getUsdRub(@Path("formatDate") formatDate: String): UsdRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$HKD_SHORT_NAME_FOR_DATE")
    suspend fun getHkdRub(@Path("formatDate") formatDate: String): HkdRubDto

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

        private const val QUERY_START_FOR_DATE = "currency-api@"
        private const val QUERY_END_FOR_DATE = "/v1/currencies/"

        private const val AUD_SHORT_NAME_FOR_DATE = "aud.json"
        private const val BTC_SHORT_NAME_FOR_DATE = "btc.json"
        private const val CAD_SHORT_NAME_FOR_DATE = "cad.json"
        private const val CHF_SHORT_NAME_FOR_DATE = "chf.json"
        private const val CNY_SHORT_NAME_FOR_DATE = "cny.json"
        private const val ETH_SHORT_NAME_FOR_DATE = "eth.json"
        private const val EUR_SHORT_NAME_FOR_DATE = "eur.json"
        private const val GBP_SHORT_NAME_FOR_DATE = "gbp.json"
        private const val JPY_SHORT_NAME_FOR_DATE = "jpy.json"
        private const val USD_SHORT_NAME_FOR_DATE = "usd.json"
        private const val HKD_SHORT_NAME_FOR_DATE = "hkd.json"
    }
}