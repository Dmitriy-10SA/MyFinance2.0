package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetJpyRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Jpy {
        return repository.getJpyRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Jpy {
        return repository.getJpyRub(date)
    }
}