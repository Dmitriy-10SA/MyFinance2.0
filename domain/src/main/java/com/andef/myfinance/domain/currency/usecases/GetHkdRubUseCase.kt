package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetHkdRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Hkd {
        return repository.getHkdRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Hkd {
        return repository.getHkdRub(date)
    }
}