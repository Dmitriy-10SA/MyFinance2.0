package com.andef.myfinance.domain.database.income.usecases

import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import javax.inject.Inject

class GetIncomesAmountUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(incomes: List<Income>): List<Double> {
        return repository.getIncomesAmount(incomes)
    }
}