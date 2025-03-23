package com.andef.myfinance.domain.income.usecases

import com.andef.myfinance.domain.income.repository.IncomeRepository
import javax.inject.Inject

class RemoveIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(id: Int) {
        repository.removeIncome(id)
    }
}