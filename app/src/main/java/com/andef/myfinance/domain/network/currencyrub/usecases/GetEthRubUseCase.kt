package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.EthRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetEthRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): EthRub {
        return repository.getEthRub()
    }
}