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
import java.util.Date

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

    suspend fun getAudRub(date: Date): AudRub
    suspend fun getBtcRub(date: Date): BtcRub
    suspend fun getCadRub(date: Date): CadRub
    suspend fun getChfRub(date: Date): ChfRub
    suspend fun getCnyRub(date: Date): CnyRub
    suspend fun getEthRub(date: Date): EthRub
    suspend fun getEurRub(date: Date): EurRub
    suspend fun getGbpRub(date: Date): GbpRub
    suspend fun getJpyRub(date: Date): JpyRub
    suspend fun getUsdRub(date: Date): UsdRub
    suspend fun getHkdRub(date: Date): HkdRub
}