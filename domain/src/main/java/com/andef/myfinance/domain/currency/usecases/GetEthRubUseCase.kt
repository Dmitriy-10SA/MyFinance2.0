package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.domain.currency.repository.CurrencyRepository
import java.util.Date
import javax.inject.Inject

class GetEthRubUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    suspend fun execute(): CurrencyRub.Eth {
        return repository.getEthRub()
    }

    suspend fun execute(date: Date): CurrencyRub.Eth {
        return repository.getEthRub(date)
    }
}