package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetCadRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Cad {
        return repository.getCadRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Cad {
        return repository.getCadRub(date)
    }
}