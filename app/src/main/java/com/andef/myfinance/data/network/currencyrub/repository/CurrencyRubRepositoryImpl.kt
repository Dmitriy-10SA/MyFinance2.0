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
import com.andef.myfinance.domain.network.currencyrub.entities.CurrencyRub
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
    override suspend fun getCurrencyRubList(): List<CurrencyRub> {
        TODO()
    }
}