package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.CnyRub
import java.util.Date
import javax.inject.Inject

class GetCnyRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.CnyRub {
        return repository.getCnyRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.CnyRub {
        return repository.getCnyRub(date)
    }
}