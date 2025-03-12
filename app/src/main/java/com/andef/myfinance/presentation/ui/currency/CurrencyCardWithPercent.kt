package com.andef.myfinance.presentation.ui.currency

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub
import com.andef.myfinance.domain.network.currency.entities.aud.AudRub
import com.andef.myfinance.presentation.formatter.AmountFormatter
import com.andef.myfinance.presentation.formatter.PercentFormatter
import com.andef.myfinance.ui.theme.MyFinanceTheme

@Composable
fun CurrencyCardWithPercent(currencyRub: CurrencyRub, percent: Double, isDarkTheme: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(currencyRub.iconResId),
                contentDescription = stringResource(currencyRub.nameResId)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(currencyRub.nameResId))
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.padding(start = 6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = AmountFormatter.format(currencyRub.amount),
                )
                Text(
                    text = PercentFormatter.format(percent),
                    color = if (percent >= 0) {
                        colorResource(R.color.my_green_black)
                    } else {
                        if (isDarkTheme) {
                            colorResource(R.color.my_red_black)
                        } else {
                            colorResource(R.color.my_red)
                        }
                    },
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun TestDark() {
    MyFinanceTheme(darkTheme = true) {
        CurrencyCardWithPercent(AudRub(1000.00), -100.1414, true)
    }
}

@Preview
@Composable
private fun TestLight() {
    MyFinanceTheme(darkTheme = false) {
        CurrencyCardWithPercent(AudRub(1000.00), -100.11244, false)
    }
}