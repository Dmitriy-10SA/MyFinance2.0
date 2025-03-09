package com.andef.myfinance.data.network.currencyrub.repository

import com.andef.myfinance.data.network.currencyrub.api.CurrencyRubApiService
import com.andef.myfinance.data.network.currencyrub.dto.aud.AudRubDtoToAudRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.btc.BtcRubDtoToBtcRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.cad.CadRubDtoToCadRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.chf.ChfRubDtoToChfRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.cny.CnyRubDtoToCnyRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.eth.EthRubDtoToEthRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.eur.EurRubDtoToEurRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.gbp.GbpRubDtoToGbpRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.jpy.JpyRubDtoToJpyRubMapper
import com.andef.myfinance.data.network.currencyrub.dto.usd.UsdRubDtoToUsdRubMapper
import com.andef.myfinance.domain.network.currencyrub.entities.AudRub
import com.andef.myfinance.domain.network.currencyrub.entities.BtcRub
import com.andef.myfinance.domain.network.currencyrub.entities.CadRub
import com.andef.myfinance.domain.network.currencyrub.entities.ChfRub
import com.andef.myfinance.domain.network.currencyrub.entities.CnyRub
import com.andef.myfinance.domain.network.currencyrub.entities.EthRub
import com.andef.myfinance.domain.network.currencyrub.entities.EurRub
import com.andef.myfinance.domain.network.currencyrub.entities.GbpRub
import com.andef.myfinance.domain.network.currencyrub.entities.JpyRub
import com.andef.myfinance.domain.network.currencyrub.entities.UsdRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
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
    private val usdRubMapper: UsdRubDtoToUsdRubMapper
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
}