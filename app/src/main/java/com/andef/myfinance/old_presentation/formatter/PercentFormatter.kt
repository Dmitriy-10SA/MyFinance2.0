package com.andef.myfinance.old_presentation.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.myfinance.R
import java.math.RoundingMode

object PercentFormatter {
    @Composable
    fun format(percent: Double): String {
        return "${
            percent.toBigDecimal().setScale(
                2,
                RoundingMode.DOWN
            ).toPlainString()
        }${stringResource(R.string.percent_sing)}"
    }
}