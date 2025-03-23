package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetUsdRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Usd {
        return repository.getUsdRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Usd {
        return repository.getUsdRub(date)
    }
}