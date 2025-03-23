package com.andef.myfinance.domain.income.usecases

import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.repository.IncomeRepository
import javax.inject.Inject

class AddIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(income: Income) {
        repository.addIncome(income)
    }
}