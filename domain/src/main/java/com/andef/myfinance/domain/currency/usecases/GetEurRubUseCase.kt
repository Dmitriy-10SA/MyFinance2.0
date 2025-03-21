package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.EurRub
import java.util.Date
import javax.inject.Inject

class GetEurRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.EurRub {
        return repository.getEurRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.EurRub {
        return repository.getEurRub(date)
    }
}