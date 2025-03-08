package com.andef.myfinance.data.database.income.mapper

import com.andef.myfinance.data.database.income.model.IncomeModel
import com.andef.myfinance.domain.database.income.entities.Income
import java.util.Date

class IncomeModelListToIncomeListMapper {
    private fun map(incomeModel: IncomeModel): Income {
        return Income(
            id = incomeModel.id,
            amount = incomeModel.amount,
            category = incomeModel.category,
            comment = incomeModel.comment,
            date = Date(incomeModel.date)
        )
    }

    fun map(incomeModelList: List<IncomeModel>): List<Income> {
        return incomeModelList.map {
            this.map(it)
        }
    }
}