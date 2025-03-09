package com.andef.myfinance.domain.network.currencyrub.repository

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

interface CurrencyRubRepository {
    suspend fun getAudRub(): AudRub
    suspend fun getBtcRub(): BtcRub
    suspend fun getCadRub(): CadRub
    suspend fun getChfRub(): ChfRub
    suspend fun getCnyRub(): CnyRub
    suspend fun getEthRub(): EthRub
    suspend fun getEurRub(): EurRub
    suspend fun getGbpRub(): GbpRub
    suspend fun getJpyRub(): JpyRub
    suspend fun getUsdRub(): UsdRub
}