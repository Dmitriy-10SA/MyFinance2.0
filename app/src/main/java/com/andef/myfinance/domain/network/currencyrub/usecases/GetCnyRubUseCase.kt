package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.CnyRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetCnyRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CnyRub {
        return repository.getCnyRub()
    }
}