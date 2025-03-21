package com.andef.myfinance.data.database.income.mapper

import com.andef.myfinance.data.database.income.model.IncomeModel
import com.andef.myfinance.domain.income.entities.Income
import java.util.Date
import javax.inject.Inject

class IncomeModelListToIncomeListMapper @Inject constructor() {
    private fun map(incomeModel: IncomeModel): com.andef.myfinance.domain.income.entities.Income {
        return com.andef.myfinance.domain.income.entities.Income(
            id = incomeModel.id,
            amount = incomeModel.amount,
            category = incomeModel.category,
            comment = incomeModel.comment,
            date = Date(incomeModel.date)
        )
    }

    fun map(incomeModelList: List<IncomeModel>): List<com.andef.myfinance.domain.income.entities.Income> {
        return incomeModelList.map {
            this.map(it)
        }
    }
}