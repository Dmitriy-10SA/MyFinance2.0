package com.andef.myfinance.domain.network.currency.usecases.eth

import com.andef.myfinance.domain.network.currency.entities.eth.EthRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetEthRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): EthRub {
        return repository.getEthRub()
    }

    suspend fun execute(date: Date): EthRub {
        return repository.getEthRub(date)
    }
}