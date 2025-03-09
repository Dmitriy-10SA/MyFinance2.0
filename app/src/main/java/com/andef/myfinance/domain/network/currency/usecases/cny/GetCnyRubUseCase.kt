package com.andef.myfinance.domain.network.currency.usecases.cny

import com.andef.myfinance.domain.network.currency.entities.cny.CnyRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import javax.inject.Inject

class GetCnyRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CnyRub {
        return repository.getCnyRub()
    }
}