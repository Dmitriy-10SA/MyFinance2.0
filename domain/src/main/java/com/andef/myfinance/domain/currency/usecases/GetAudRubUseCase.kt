package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.AudRub
import java.util.Date
import javax.inject.Inject

class GetAudRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.AudRub {
        return repository.getAudRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.AudRub {
        return repository.getAudRub(date)
    }
}