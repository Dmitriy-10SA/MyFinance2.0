package com.andef.myfinance.domain.database.income.usecases

import androidx.lifecycle.LiveData
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import java.util.Date
import javax.inject.Inject

class GetFullAmountUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    fun execute(startDate: Date, endDate: Date): LiveData<Double> {
        return repository.getFullAmount(startDate, endDate)
    }
}