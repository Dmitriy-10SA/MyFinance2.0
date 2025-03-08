package com.andef.myfinance.domain.database.income.usecases

import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import java.util.Date
import javax.inject.Inject

class ChangeIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(
        income: Income,
        newAmount: Double? = null,
        newCategory: IncomeCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    ) {
        repository.changeIncome(
            income,
            newAmount,
            newCategory,
            newComment,
            newDate
        )
    }
}