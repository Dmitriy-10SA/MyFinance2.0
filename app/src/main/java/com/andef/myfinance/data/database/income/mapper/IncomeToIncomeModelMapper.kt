package com.andef.myfinance.data.database.income.mapper

import com.andef.myfinance.data.database.income.model.IncomeModel
import com.andef.myfinance.domain.database.income.entities.Income
import javax.inject.Inject

class IncomeToIncomeModelMapper @Inject constructor() {
    fun map(income: Income): IncomeModel {
        return IncomeModel(
            id = income.id,
            amount = income.amount,
            category = income.category,
            comment = income.comment,
            date = income.date.time
        )
    }
}