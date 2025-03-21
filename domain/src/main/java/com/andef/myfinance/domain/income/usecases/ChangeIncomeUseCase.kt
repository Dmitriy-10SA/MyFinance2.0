package com.andef.myfinance.domain.income.usecases

import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.domain.income.repository.IncomeRepository
import java.util.Date
import javax.inject.Inject

class ChangeIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(
        id: Int,
        amount: Double,
        category: IncomeCategory,
        comment: String,
        date: Date
    ) {
        repository.changeIncome(id, amount, category, comment, date)
    }
}