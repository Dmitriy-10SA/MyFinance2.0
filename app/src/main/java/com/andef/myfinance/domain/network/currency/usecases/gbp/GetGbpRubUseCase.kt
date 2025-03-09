package com.andef.myfinance.domain.network.currency.usecases.gbp

import com.andef.myfinance.domain.network.currency.entities.gbp.GbpRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import javax.inject.Inject

class GetGbpRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): GbpRub {
        return repository.getGbpRub()
    }
}