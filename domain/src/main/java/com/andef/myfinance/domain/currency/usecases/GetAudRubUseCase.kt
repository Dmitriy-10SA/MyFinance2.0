package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetAudRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CurrencyRub.Aud {
        return repository.getAudRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Aud {
        return repository.getAudRub(date)
    }
}