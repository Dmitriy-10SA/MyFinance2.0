package com.andef.myfinance.domain.network.currencyrub.repository

import com.andef.myfinance.domain.network.currencyrub.entities.CurrencyRub

interface CurrencyRubRepository {
    suspend fun getCurrencyRubList(): List<CurrencyRub>
}