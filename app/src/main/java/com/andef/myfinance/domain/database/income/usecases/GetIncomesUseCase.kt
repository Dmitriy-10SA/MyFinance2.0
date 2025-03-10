package com.andef.myfinance.domain.database.income.usecases

import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetIncomesUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    fun execute(startDate: Date, endDate: Date): Flow<List<Income>> {
        return repository.getIncomes(startDate, endDate)
    }
}