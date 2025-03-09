package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.GbpRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetGbpRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): GbpRub {
        return repository.getGbpRub()
    }
}