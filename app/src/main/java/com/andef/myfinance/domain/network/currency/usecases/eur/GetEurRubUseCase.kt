package com.andef.myfinance.domain.network.currency.usecases.eur

import com.andef.myfinance.domain.network.currency.entities.eur.EurRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetEurRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): EurRub {
        return repository.getEurRub()
    }

    suspend fun execute(date: Date): EurRub {
        return repository.getEurRub(date)
    }
}