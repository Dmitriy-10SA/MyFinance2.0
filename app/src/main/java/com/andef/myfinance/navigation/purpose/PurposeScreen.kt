package com.andef.myfinance.navigation.purpose

sealed class PurposeScreen(val route: String) {
    data object MainPurposeScreen : PurposeScreen(MAIN_PURPOSE_SCREEN_ROUTE) {
        data object ActivePurpose : PurposeScreen(ACTIVE_PURPOSE)
        data object CompletedPurpose : PurposeScreen(COMPLETED_PURPOSE)

        private const val ACTIVE_PURPOSE = "active_purpose"
        private const val COMPLETED_PURPOSE = "completed_purpose"
    }

    companion object {
        private const val MAIN_PURPOSE_SCREEN_ROUTE = "main_purpose_screen"
    }
}