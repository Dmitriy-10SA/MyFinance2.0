package com.andef.myfinance.domain.network.currency.usecases.eur

import com.andef.myfinance.domain.network.currency.entities.eur.EurRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import javax.inject.Inject

class GetEurRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): EurRub {
        return repository.getEurRub()
    }
}