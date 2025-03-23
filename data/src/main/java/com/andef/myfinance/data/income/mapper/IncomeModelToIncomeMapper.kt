package com.andef.myfinance.data.income.mapper

import com.andef.myfinance.data.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.Income
import java.util.Date

internal object IncomeModelToIncomeMapper {
    fun map(incomeModel: IncomeModel): Income {
        return Income(
            id = incomeModel.id,
            amount = incomeModel.amount,
            category = incomeModel.category,
            comment = incomeModel.comment,
            date = Date(incomeModel.date)
        )
    }
}