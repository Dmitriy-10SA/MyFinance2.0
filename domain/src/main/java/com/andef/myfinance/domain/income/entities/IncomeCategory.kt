package com.andef.myfinance.domain.income.entities

//категория дохода
sealed class IncomeCategory {
    data object Salary : IncomeCategory()
    data object Bank : IncomeCategory()
    data object Luck : IncomeCategory()
    data object Gifts : IncomeCategory()
    data object Other : IncomeCategory()
}