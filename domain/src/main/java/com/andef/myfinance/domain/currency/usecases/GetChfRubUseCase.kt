package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetChfRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CurrencyRub.Chf {
        return repository.getChfRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Chf {
        return repository.getChfRub(date)
    }
}