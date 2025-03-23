package com.andef.myfinance.data.income.mapper

import com.andef.myfinance.data.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.Income

internal object IncomeToIncomeModelMapper {
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