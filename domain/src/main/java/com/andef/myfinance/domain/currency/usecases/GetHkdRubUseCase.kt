package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.HkdRub
import java.util.Date
import javax.inject.Inject

class GetHkdRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.HkdRub {
        return repository.getHkdRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.HkdRub {
        return repository.getHkdRub(date)
    }
}