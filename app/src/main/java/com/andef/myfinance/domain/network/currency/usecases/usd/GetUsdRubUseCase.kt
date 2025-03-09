package com.andef.myfinance.domain.network.currency.usecases.usd

import com.andef.myfinance.domain.network.currency.entities.usd.UsdRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import javax.inject.Inject

class GetUsdRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): UsdRub {
        return repository.getUsdRub()
    }
}