package com.andef.myfinance.presentation.analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import com.andef.myfinance.utils.ui.toDate
import java.time.LocalDate
import java.util.Date

@Composable
fun AnalysisSetDate(
    topBarState: MutableState<AnalysisTopBarItem>,
    startDate: MutableState<Date>,
    endDate: MutableState<Date>
) {
    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            AnalysisTopBarItem.Day -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().toDate()
            }

            AnalysisTopBarItem.Week -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusWeeks(1).toDate()
            }

            AnalysisTopBarItem.Month -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusMonths(1).toDate()
            }

            AnalysisTopBarItem.Year -> {
                endDate.value = LocalDate.now().toDate()
                startDate.value = LocalDate.now().minusYears(1).toDate()
            }

            AnalysisTopBarItem.Period -> {}
        }
    }
}