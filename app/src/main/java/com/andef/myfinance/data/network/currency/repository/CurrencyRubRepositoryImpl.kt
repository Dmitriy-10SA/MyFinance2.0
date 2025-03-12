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
import com.andef.myfinance.domain.network.currency.entities.aud.AudRub
import com.andef.myfinance.domain.network.currency.entities.btc.BtcRub
import com.andef.myfinance.domain.network.currency.entities.cad.CadRub
import com.andef.myfinance.domain.network.currency.entities.chf.ChfRub
import com.andef.myfinance.domain.network.currency.entities.cny.CnyRub
import com.andef.myfinance.domain.network.currency.entities.eth.EthRub
import com.andef.myfinance.domain.network.currency.entities.eur.EurRub
import com.andef.myfinance.domain.network.currency.entities.gbp.GbpRub
import com.andef.myfinance.domain.network.currency.entities.hkd.HkdRub
import com.andef.myfinance.domain.network.currency.entities.jpy.JpyRub
import com.andef.myfinance.domain.network.currency.entities.usd.UsdRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
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
) : CurrencyRubRepository {
    override suspend fun getAudRub(): AudRub {
        return audRubMapper.map(apiService.getAudRub())
    }

    override suspend fun getBtcRub(): BtcRub {
        return btcRubMapper.map(apiService.getBtcRub())
    }

    override suspend fun getCadRub(): CadRub {
        return cadRubMapper.map(apiService.getCadRub())
    }

    override suspend fun getChfRub(): ChfRub {
        return chfRubMapper.map(apiService.getChfRub())
    }

    override suspend fun getCnyRub(): CnyRub {
        return cnyRubMapper.map(apiService.getCnyRub())
    }

    override suspend fun getEthRub(): EthRub {
        return ethRubMapper.map(apiService.getEthRub())
    }

    override suspend fun getEurRub(): EurRub {
        return eurRubMapper.map(apiService.getEurRub())
    }

    override suspend fun getGbpRub(): GbpRub {
        return gbpRubMapper.map(apiService.getGbpRub())
    }

    override suspend fun getJpyRub(): JpyRub {
        return jpyRubMapper.map(apiService.getJpyRub())
    }

    override suspend fun getUsdRub(): UsdRub {
        return usdRubMapper.map(apiService.getUsdRub())
    }

    override suspend fun getHkdRub(): HkdRub {
        return hkdRubMapper.map(apiService.getHkdRub())
    }
}