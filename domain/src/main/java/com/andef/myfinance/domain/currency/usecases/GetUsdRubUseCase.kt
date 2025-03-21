package com.andef.myfinance.domain.currency.usecases

import com.andef.myfinance.domain.currency.UsdRub
import java.util.Date
import javax.inject.Inject

class GetUsdRubUseCase @Inject constructor(
    private val repository: com.andef.myfinance.domain.currency.repository.CurrencyRubRepository
) {
    suspend fun execute(): com.andef.myfinance.domain.currency.UsdRub {
        return repository.getUsdRub()
    }

    suspend fun execute(date: Date): com.andef.myfinance.domain.currency.UsdRub {
        return repository.getUsdRub(date)
    }
}