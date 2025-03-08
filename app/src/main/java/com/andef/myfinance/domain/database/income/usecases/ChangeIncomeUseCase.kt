package com.andef.myfinance.domain.database.income.usecases

import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.domain.database.income.repository.IncomeRepository
import javax.inject.Inject

class ChangeIncomeUseCase @Inject constructor(
    private val repository: IncomeRepository
) {
    suspend fun execute(
        id: Int,
        newAmount: Double? = null,
        newCategory: IncomeCategory? = null,
        newComment: String? = null,
        newDate: String? = null
    ) {
        repository.changeIncome(
            id,
            newAmount,
            newCategory,
            newComment,
            newDate
        )
    }
}