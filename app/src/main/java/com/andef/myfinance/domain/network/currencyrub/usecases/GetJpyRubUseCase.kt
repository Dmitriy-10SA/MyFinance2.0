package com.andef.myfinance.domain.network.currencyrub.usecases

import com.andef.myfinance.domain.network.currencyrub.entities.JpyRub
import com.andef.myfinance.domain.network.currencyrub.repository.CurrencyRubRepository
import javax.inject.Inject

class GetJpyRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): JpyRub {
        return repository.getJpyRub()
    }
}