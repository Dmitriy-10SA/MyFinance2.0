package com.andef.myfinance.presentation.webview

sealed class WebViewLink(val link: String) {
    data object BankRuDepositsScreen : WebViewLink(BANK_RU_DEPOSITS_URL)
    data object BankRuCreditsScreen : WebViewLink(BANK_RU_CREDITS_URL)
    data object BankRuMicroloansScreen : WebViewLink(BANK_RU_MICROLOANS_URL)
    data object BankRuMortgageScreen : WebViewLink(BANK_RU_MORTGAGE_URL)
    data object BankRuNewsScreen : WebViewLink(BANK_RU_NEWS_URL)

    companion object {
        private const val BANK_RU_DEPOSITS_URL =
            "https://www.banki.ru/products/deposits/?place=main_menu_deposits"
        private const val BANK_RU_CREDITS_URL = "https://www.banki.ru/credit-master/"
        private const val BANK_RU_MICROLOANS_URL = "https://www.banki.ru/microloans/"
        private const val BANK_RU_MORTGAGE_URL =
            "https://www.banki.ru/products/hypothec/?place=submenu_hypothec"
        private const val BANK_RU_NEWS_URL = "https://www.banki.ru/news/?source=main_menu_news"
    }
}