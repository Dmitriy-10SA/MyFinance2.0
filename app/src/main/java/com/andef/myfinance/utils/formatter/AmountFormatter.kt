package com.andef.myfinance.utils.formatter

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.andef.myfinance.R
import java.math.RoundingMode

internal object AmountFormatter {
    @Composable
    fun format(amount: Double): String {
        return "${
            amount.toBigDecimal().setScale(
                2,
                RoundingMode.DOWN
            ).toPlainString()
        }${stringResource(R.string.rub_sing)}"
    }
}