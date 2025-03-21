package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.CadRub
import java.util.Date
import javax.inject.Inject

class GetCadRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.CadRub {
        return repository.getCadRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.CadRub {
        return repository.getCadRub(date)
    }
}