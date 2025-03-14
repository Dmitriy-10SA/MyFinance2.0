package com.andef.myfinance.domain.network.currency.usecases.hkd

import com.andef.myfinance.domain.network.currency.entities.hkd.HkdRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetHkdRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): HkdRub {
        return repository.getHkdRub()
    }

    suspend fun execute(date: Date): HkdRub {
        return repository.getHkdRub(date)
    }
}