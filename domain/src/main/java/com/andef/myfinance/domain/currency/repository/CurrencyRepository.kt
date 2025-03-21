package com.andef.myfinance.domain.currency.repository

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import java.util.Date

interface CurrencyRepository {
    suspend fun getAudRub(): CurrencyRub.Aud
    suspend fun getBtcRub(): CurrencyRub.Btc
    suspend fun getCadRub(): CurrencyRub.Cad
    suspend fun getChfRub(): CurrencyRub.Chf
    suspend fun getCnyRub(): CurrencyRub.Cny
    suspend fun getEthRub(): CurrencyRub.Eth
    suspend fun getEurRub(): CurrencyRub.Eur
    suspend fun getGbpRub(): CurrencyRub.Gbp
    suspend fun getJpyRub(): CurrencyRub.Jpy
    suspend fun getUsdRub(): CurrencyRub.Usd
    suspend fun getHkdRub(): CurrencyRub.Hkd
    suspend fun getAudRub(date: Date): CurrencyRub.Aud
    suspend fun getBtcRub(date: Date): CurrencyRub.Btc
    suspend fun getCadRub(date: Date): CurrencyRub.Cad
    suspend fun getChfRub(date: Date): CurrencyRub.Chf
    suspend fun getCnyRub(date: Date): CurrencyRub.Cny
    suspend fun getEthRub(date: Date): CurrencyRub.Eth
    suspend fun getEurRub(date: Date): CurrencyRub.Eur
    suspend fun getGbpRub(date: Date): CurrencyRub.Gbp
    suspend fun getJpyRub(date: Date): CurrencyRub.Jpy
    suspend fun getUsdRub(date: Date): CurrencyRub.Usd
    suspend fun getHkdRub(date: Date): CurrencyRub.Hkd
}