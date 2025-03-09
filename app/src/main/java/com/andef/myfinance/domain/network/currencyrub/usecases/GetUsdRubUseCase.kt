package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.UsdRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetUsdRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): UsdRub {
        return repository.getUsdRub()
    }
}