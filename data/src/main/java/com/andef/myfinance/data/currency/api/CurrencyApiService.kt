package com.andef.myfinance.data.currency.api


import com.andef.myfinance.data.currency.dto.CurrencyRubDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET(AUD)
    suspend fun getAudRub(): CurrencyRubDto.AudRubDto

    @GET(BTC)
    suspend fun getBtcRub(): CurrencyRubDto.BtcRubDto

    @GET(CAD)
    suspend fun getCadRub(): CurrencyRubDto.CadRubDto

    @GET(CHF)
    suspend fun getChfRub(): CurrencyRubDto.ChfRubDto

    @GET(CNY)
    suspend fun getCnyRub(): CurrencyRubDto.CnyRubDto

    @GET(ETH)
    suspend fun getEthRub(): CurrencyRubDto.EthRubDto

    @GET(EUR)
    suspend fun getEurRub(): CurrencyRubDto.EurRubDto

    @GET(GBP)
    suspend fun getGbpRub(): CurrencyRubDto.GbpRubDto

    @GET(JPY)
    suspend fun getJpyRub(): CurrencyRubDto.JpyRubDto

    @GET(USD)
    suspend fun getUsdRub(): CurrencyRubDto.UsdRubDto

    @GET(HKD)
    suspend fun getHkdRub(): CurrencyRubDto.HkdRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$AUD_SHORT_NAME_FOR_DATE")
    suspend fun getAudRub(@Path("formatDate") formatDate: String): CurrencyRubDto.AudRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$BTC_SHORT_NAME_FOR_DATE")
    suspend fun getBtcRub(@Path("formatDate") formatDate: String): CurrencyRubDto.BtcRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CAD_SHORT_NAME_FOR_DATE")
    suspend fun getCadRub(@Path("formatDate") formatDate: String): CurrencyRubDto.CadRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CHF_SHORT_NAME_FOR_DATE")
    suspend fun getChfRub(@Path("formatDate") formatDate: String): CurrencyRubDto.ChfRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$CNY_SHORT_NAME_FOR_DATE")
    suspend fun getCnyRub(@Path("formatDate") formatDate: String): CurrencyRubDto.CnyRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$ETH_SHORT_NAME_FOR_DATE")
    suspend fun getEthRub(@Path("formatDate") formatDate: String): CurrencyRubDto.EthRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$EUR_SHORT_NAME_FOR_DATE")
    suspend fun getEurRub(@Path("formatDate") formatDate: String): CurrencyRubDto.EurRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$GBP_SHORT_NAME_FOR_DATE")
    suspend fun getGbpRub(@Path("formatDate") formatDate: String): CurrencyRubDto.GbpRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$JPY_SHORT_NAME_FOR_DATE")
    suspend fun getJpyRub(@Path("formatDate") formatDate: String): CurrencyRubDto.JpyRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$USD_SHORT_NAME_FOR_DATE")
    suspend fun getUsdRub(@Path("formatDate") formatDate: String): CurrencyRubDto.UsdRubDto

    @GET("$QUERY_START_FOR_DATE{formatDate}$QUERY_END_FOR_DATE$HKD_SHORT_NAME_FOR_DATE")
    suspend fun getHkdRub(@Path("formatDate") formatDate: String): CurrencyRubDto.HkdRubDto

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