package com.andef.myfinance.domain.database.income.usecases

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import java.util.Date
import javax.inject.Inject

class GetIncomesUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    fun execute(startDate: Date, endDate: Date): LiveData<List<Income>> {
        return repository.getIncomes(startDate, endDate)
    }
}