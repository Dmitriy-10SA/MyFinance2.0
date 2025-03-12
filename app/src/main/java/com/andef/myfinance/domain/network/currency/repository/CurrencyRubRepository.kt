package com.andef.myfinance.domain.network.currency.repository

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
    suspend fun getHkdRub(): HkdRub
}