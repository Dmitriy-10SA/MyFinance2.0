package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetEurRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Eur {
        return repository.getEurRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Eur {
        return repository.getEurRub(date)
    }
}