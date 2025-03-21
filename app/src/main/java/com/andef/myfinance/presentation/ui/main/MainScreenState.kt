package com.andef.myfinance.presentation.ui.main

import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.income.entities.Income

sealed class MainScreenState {
    data object DatePickerScreen : MainScreenState()
    data object AnyScreenWithTopAndBottomNav : MainScreenState()
    data object IncomeScreenForAdd : MainScreenState()
    data object DetailIncomeScreen : MainScreenState()
    data object ExpenseScreenForAdd : MainScreenState()
    data object DetailExpenseScreen : MainScreenState()
    data class IncomeScreenForChange(val income: com.andef.myfinance.domain.income.entities.Income) : MainScreenState()
    data class ExpenseScreenForChange(val expense: com.andef.myfinance.domain.expense.entities.Expense) : MainScreenState()
    data object CurrencyScreen : MainScreenState()
    data class BankRuDepositsScreen(val url: String = BANK_RU_DEPOSITS_URL) : MainScreenState()
    data class BankRuCreditsScreen(val url: String = BANK_RU_CREDITS_URL) : MainScreenState()
    data class BankRuMicroloansScreen(val url: String = BANK_RU_MICROLOANS_URL) : MainScreenState()
    data class BankRuMortgageScreen(val url: String = BANK_RU_MORTGAGE_URL) : MainScreenState()
    data class BankRuNewsScreen(val url: String = BANK_RU_NEWS_URL) : MainScreenState()

    companion object {
        private const val BANK_RU_DEPOSITS_URL = "https://www.banki.ru/products/deposits/?place=main_menu_deposits"
        private const val BANK_RU_CREDITS_URL = "https://www.banki.ru/credit-master/"
        private const val BANK_RU_MICROLOANS_URL = "https://www.banki.ru/microloans/"
        private const val BANK_RU_MORTGAGE_URL = "https://www.banki.ru/products/hypothec/?place=submenu_hypothec"
        private const val BANK_RU_NEWS_URL = "https://www.banki.ru/news/?source=main_menu_news"
    }
}