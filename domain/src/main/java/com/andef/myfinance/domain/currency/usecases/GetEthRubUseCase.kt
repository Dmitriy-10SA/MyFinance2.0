package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.EthRub
import java.util.Date
import javax.inject.Inject

class GetEthRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.EthRub {
        return repository.getEthRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.EthRub {
        return repository.getEthRub(date)
    }
}