package com.andef.myfinance.domain.network.currency.usecases.jpy

import com.andef.myfinance.domain.network.currency.entities.jpy.JpyRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetJpyRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): JpyRub {
        return repository.getJpyRub()
    }

    suspend fun execute(date: Date): JpyRub {
        return repository.getJpyRub(date)
    }
}