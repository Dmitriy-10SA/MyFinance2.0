package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetGbpRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CurrencyRub.Gbp {
        return repository.getGbpRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Gbp {
        return repository.getGbpRub(date)
    }
}