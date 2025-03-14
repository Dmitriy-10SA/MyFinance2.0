package com.andef.myfinance.domain.network.currency.usecases.cad

import com.andef.myfinance.domain.network.currency.entities.cad.CadRub
import com.andef.myfinance.domain.network.currency.repository.CurrencyRubRepository
import java.util.Date
import javax.inject.Inject

class GetCadRubUseCase @Inject constructor(
    private val repository: CurrencyRubRepository
) {
    suspend fun execute(): CadRub {
        return repository.getCadRub()
    }

    suspend fun execute(date: Date): CadRub {
        return repository.getCadRub(date)
    }
}