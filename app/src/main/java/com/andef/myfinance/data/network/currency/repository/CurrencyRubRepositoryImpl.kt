package com.andef.myfinance.data.network.currency.repository

import com.andef.myfinance.data.network.currency.api.CurrencyRubApiService
import com.andef.myfinance.data.network.currency.mapper.aud.AudRubDtoToAudRubMapper
import com.andef.myfinance.data.network.currency.mapper.btc.BtcRubDtoToBtcRubMapper
import com.andef.myfinance.data.network.currency.mapper.cad.CadRubDtoToCadRubMapper
import com.andef.myfinance.data.network.currency.mapper.chf.ChfRubDtoToChfRubMapper
import com.andef.myfinance.data.network.currency.mapper.cny.CnyRubDtoToCnyRubMapper
import com.andef.myfinance.data.network.currency.mapper.eth.EthRubDtoToEthRubMapper
import com.andef.myfinance.data.network.currency.mapper.eur.EurRubDtoToEurRubMapper
import com.andef.myfinance.data.network.currency.mapper.gbp.GbpRubDtoToGbpRubMapper
import com.andef.myfinance.data.network.currency.mapper.hkd.HkdRubDtoToHkdRubMapper
import com.andef.myfinance.data.network.currency.mapper.jpy.JpyRubDtoToJpyRubMapper
import com.andef.myfinance.data.network.currency.mapper.usd.UsdRubDtoToUsdRubMapper
import com.andef.myfinance.domain.currency.AudRub
import com.andef.myfinance.domain.currency.BtcRub
import com.andef.myfinance.domain.currency.CadRub
import com.andef.myfinance.domain.currency.ChfRub
import com.andef.myfinance.domain.currency.CnyRub
import com.andef.myfinance.domain.currency.EthRub
import com.andef.myfinance.domain.currency.EurRub
import com.andef.myfinance.domain.currency.GbpRub
import com.andef.myfinance.domain.currency.HkdRub
import com.andef.myfinance.domain.currency.JpyRub
import com.andef.myfinance.domain.currency.UsdRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CurrencyRubRepositoryImpl @Inject constructor(
    private val apiService: CurrencyRubApiService,
    private val audRubMapper: AudRubDtoToAudRubMapper,
    private val btcRubMapper: BtcRubDtoToBtcRubMapper,
    private val cadRubMapper: CadRubDtoToCadRubMapper,
    private val chfRubMapper: ChfRubDtoToChfRubMapper,
    private val cnyRubMapper: CnyRubDtoToCnyRubMapper,
    private val ethRubMapper: EthRubDtoToEthRubMapper,
    private val eurRubMapper: EurRubDtoToEurRubMapper,
    private val gbpRubMapper: GbpRubDtoToGbpRubMapper,
    private val jpyRubMapper: JpyRubDtoToJpyRubMapper,
    private val usdRubMapper: UsdRubDtoToUsdRubMapper,
    private val hkdRubMapper: HkdRubDtoToHkdRubMapper
) : com.andef.myfinance.domain.currency.repository.CurrencyRubRepository {
    override suspend fun getAudRub(): com.andef.myfinance.domain.currency.AudRub {
        return audRubMapper.map(apiService.getAudRub())
    }

    override suspend fun getBtcRub(): com.andef.myfinance.domain.currency.BtcRub {
        return btcRubMapper.map(apiService.getBtcRub())
    }

    override suspend fun getCadRub(): com.andef.myfinance.domain.currency.CadRub {
        return cadRubMapper.map(apiService.getCadRub())
    }

    override suspend fun getChfRub(): com.andef.myfinance.domain.currency.ChfRub {
        return chfRubMapper.map(apiService.getChfRub())
    }

    override suspend fun getCnyRub(): com.andef.myfinance.domain.currency.CnyRub {
        return cnyRubMapper.map(apiService.getCnyRub())
    }

    override suspend fun getEthRub(): com.andef.myfinance.domain.currency.EthRub {
        return ethRubMapper.map(apiService.getEthRub())
    }

    override suspend fun getEurRub(): com.andef.myfinance.domain.currency.EurRub {
        return eurRubMapper.map(apiService.getEurRub())
    }

    override suspend fun getGbpRub(): com.andef.myfinance.domain.currency.GbpRub {
        return gbpRubMapper.map(apiService.getGbpRub())
    }

    override suspend fun getJpyRub(): com.andef.myfinance.domain.currency.JpyRub {
        return jpyRubMapper.map(apiService.getJpyRub())
    }

    override suspend fun getUsdRub(): com.andef.myfinance.domain.currency.UsdRub {
        return usdRubMapper.map(apiService.getUsdRub())
    }

    override suspend fun getHkdRub(): com.andef.myfinance.domain.currency.HkdRub {
        return hkdRubMapper.map(apiService.getHkdRub())
    }

    override suspend fun getAudRub(date: Date): com.andef.myfinance.domain.currency.AudRub {
        return audRubMapper.map(apiService.getAudRub(formatDate(date)))
    }

    override suspend fun getBtcRub(date: Date): com.andef.myfinance.domain.currency.BtcRub {
        return btcRubMapper.map(apiService.getBtcRub(formatDate(date)))
    }

    override suspend fun getCadRub(date: Date): com.andef.myfinance.domain.currency.CadRub {
        return cadRubMapper.map(apiService.getCadRub(formatDate(date)))
    }

    override suspend fun getChfRub(date: Date): com.andef.myfinance.domain.currency.ChfRub {
        return chfRubMapper.map(apiService.getChfRub(formatDate(date)))
    }

    override suspend fun getCnyRub(date: Date): com.andef.myfinance.domain.currency.CnyRub {
        return cnyRubMapper.map(apiService.getCnyRub(formatDate(date)))
    }

    override suspend fun getEthRub(date: Date): com.andef.myfinance.domain.currency.EthRub {
        return ethRubMapper.map(apiService.getEthRub(formatDate(date)))
    }

    override suspend fun getEurRub(date: Date): com.andef.myfinance.domain.currency.EurRub {
        return eurRubMapper.map(apiService.getEurRub(formatDate(date)))
    }

    override suspend fun getGbpRub(date: Date): com.andef.myfinance.domain.currency.GbpRub {
        return gbpRubMapper.map(apiService.getGbpRub(formatDate(date)))
    }

    override suspend fun getJpyRub(date: Date): com.andef.myfinance.domain.currency.JpyRub {
        return jpyRubMapper.map(apiService.getJpyRub(formatDate(date)))
    }

    override suspend fun getUsdRub(date: Date): com.andef.myfinance.domain.currency.UsdRub {
        return usdRubMapper.map(apiService.getUsdRub(formatDate(date)))
    }

    override suspend fun getHkdRub(date: Date): com.andef.myfinance.domain.currency.HkdRub {
        return hkdRubMapper.map(apiService.getHkdRub(formatDate(date)))
    }

    private fun formatDate(date: Date): String {
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return outputFormat.format(date)
    }
}