package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.JpyRub
import java.util.Date
import javax.inject.Inject

class GetJpyRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.JpyRub {
        return repository.getJpyRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.JpyRub {
        return repository.getJpyRub(date)
    }
}