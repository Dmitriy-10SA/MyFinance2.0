package com.andef.myfinance.domain.network.currency.usecases.aud

import com.andef.myfinance.domain.network.currency.entities.aud.AudRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetAudRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): AudRub {
        return repository.getAudRub()
    }

    suspend fun execute(date: Date): AudRub {
        return repository.getAudRub(date)
    }
}