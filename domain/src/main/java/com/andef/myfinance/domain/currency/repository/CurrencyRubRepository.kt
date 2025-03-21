package com.andef.myfinance.domain.currency.repository

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