package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.ChfRub
import java.util.Date
import javax.inject.Inject

class GetChfRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.ChfRub {
        return repository.getChfRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.ChfRub {
        return repository.getChfRub(date)
    }
}