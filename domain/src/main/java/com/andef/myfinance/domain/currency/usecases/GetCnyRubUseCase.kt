package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetCnyRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CurrencyRub.Cny {
        return repository.getCnyRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Cny {
        return repository.getCnyRub(date)
    }
}