package com.andef.myfinance.data.currency.repository

import com.andef.myfinance.data.currency.api.CurrencyApiService
import com.andef.myfinance.data.currency.mapper.CurrencyRubDtoToCurrencyRubMapper
import com.andef.myfinance.domain.currency.entities.CurrencyRub
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val apiService: CurrencyApiService
) : com.andef.myfinance.domain.currency.repository.CurrencyRepository {
    override suspend fun getAudRub(): CurrencyRub.Aud {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getAudRub()
        ) as CurrencyRub.Aud
    }

    override suspend fun getAudRub(date: Date): CurrencyRub.Aud {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getAudRub(formatDate(date))
        ) as CurrencyRub.Aud
    }

    override suspend fun getBtcRub(): CurrencyRub.Btc {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getBtcRub()
        ) as CurrencyRub.Btc
    }

    override suspend fun getBtcRub(date: Date): CurrencyRub.Btc {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getBtcRub(formatDate(date))
        ) as CurrencyRub.Btc
    }

    override suspend fun getCadRub(): CurrencyRub.Cad {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getCadRub()
        ) as CurrencyRub.Cad
    }

    override suspend fun getCadRub(date: Date): CurrencyRub.Cad {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getCadRub(formatDate(date))
        ) as CurrencyRub.Cad
    }

    override suspend fun getChfRub(): CurrencyRub.Chf {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getChfRub()
        ) as CurrencyRub.Chf
    }

    override suspend fun getChfRub(date: Date): CurrencyRub.Chf {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getChfRub(formatDate(date))
        ) as CurrencyRub.Chf
    }

    override suspend fun getCnyRub(): CurrencyRub.Cny {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getCnyRub()
        ) as CurrencyRub.Cny
    }

    override suspend fun getCnyRub(date: Date): CurrencyRub.Cny {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getCnyRub(formatDate(date))
        ) as CurrencyRub.Cny
    }

    override suspend fun getEthRub(): CurrencyRub.Eth {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getEthRub()
        ) as CurrencyRub.Eth
    }

    override suspend fun getEthRub(date: Date): CurrencyRub.Eth {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getEthRub(formatDate(date))
        ) as CurrencyRub.Eth
    }

    override suspend fun getEurRub(): CurrencyRub.Eur {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getEurRub()
        ) as CurrencyRub.Eur
    }

    override suspend fun getEurRub(date: Date): CurrencyRub.Eur {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getEurRub(formatDate(date))
        ) as CurrencyRub.Eur
    }

    override suspend fun getGbpRub(): CurrencyRub.Gbp {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getGbpRub()
        ) as CurrencyRub.Gbp
    }

    override suspend fun getGbpRub(date: Date): CurrencyRub.Gbp {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getGbpRub(formatDate(date))
        ) as CurrencyRub.Gbp
    }

    override suspend fun getJpyRub(): CurrencyRub.Jpy {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getJpyRub()
        ) as CurrencyRub.Jpy
    }

    override suspend fun getJpyRub(date: Date): CurrencyRub.Jpy {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getJpyRub(formatDate(date))
        ) as CurrencyRub.Jpy
    }

    override suspend fun getUsdRub(): CurrencyRub.Usd {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getUsdRub()
        ) as CurrencyRub.Usd
    }

    override suspend fun getUsdRub(date: Date): CurrencyRub.Usd {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getUsdRub(formatDate(date))
        ) as CurrencyRub.Usd
    }

    override suspend fun getHkdRub(): CurrencyRub.Hkd {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getHkdRub()
        ) as CurrencyRub.Hkd
    }

    override suspend fun getHkdRub(date: Date): CurrencyRub.Hkd {
        return CurrencyRubDtoToCurrencyRubMapper.map(
            apiService.getHkdRub(formatDate(date))
        ) as CurrencyRub.Hkd
    }

    private fun formatDate(date: Date): String {
        val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return outputFormat.format(date)
    }
}