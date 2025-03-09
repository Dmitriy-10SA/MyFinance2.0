package com.andef.myfinance.domain.network.currency.usecases.chf

import com.andef.myfinance.domain.network.currency.entities.chf.ChfRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import javax.inject.Inject

class GetChfRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): ChfRub {
        return repository.getChfRub()
    }
}