package com.andef.myfinance.domain.income.usecases

import com.andef.myfinance.domain.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetFullIncomeAmountUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    fun execute(startDate: Date, endDate: Date): Flow<Double> {
        return repository.getFullIncomeAmount(startDate, endDate)
    }
}