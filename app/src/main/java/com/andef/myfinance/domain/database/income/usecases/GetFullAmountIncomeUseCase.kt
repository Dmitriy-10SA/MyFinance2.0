package com.andef.myfinance.domain.database.income.usecases

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import java.util.Date
import javax.inject.Inject

class GetFullAmountIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    fun execute(startDate: Date, endDate: Date): LiveData<Double> {
        return repository.getFullAmount(startDate, endDate)
    }
}