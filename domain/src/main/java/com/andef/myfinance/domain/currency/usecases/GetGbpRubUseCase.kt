package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.GbpRub
import java.util.Date
import javax.inject.Inject

class GetGbpRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.GbpRub {
        return repository.getGbpRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.GbpRub {
        return repository.getGbpRub(date)
    }
}