package com.andef.myfinance.presentation.currency

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.formatter.PercentFormatter

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
                painter = painterResource(getCurrencyRubIconResId(currencyRub)),
                contentDescription = stringResource(getCurrencyRubNameResId(currencyRub))
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(getCurrencyRubNameResId(currencyRub)))
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.padding(start = 6.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = AmountFormatter.format(getCurrencyAmount(currencyRub)),
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

private fun getCurrencyRubNameResId(currencyRub: CurrencyRub): Int {
    return when (currencyRub) {
        is CurrencyRub.Aud -> R.string.aud
        is CurrencyRub.Btc -> R.string.btc
        is CurrencyRub.Cad -> R.string.cad
        is CurrencyRub.Chf -> R.string.chf
        is CurrencyRub.Cny -> R.string.cny
        is CurrencyRub.Eth -> R.string.eth
        is CurrencyRub.Eur -> R.string.eur
        is CurrencyRub.Gbp -> R.string.gbp
        is CurrencyRub.Hkd -> R.string.hkd
        is CurrencyRub.Jpy -> R.string.jpy
        is CurrencyRub.Usd -> R.string.usd
    }
}

private fun getCurrencyRubIconResId(currencyRub: CurrencyRub): Int {
    return when (currencyRub) {
        is CurrencyRub.Aud -> R.drawable.aud
        is CurrencyRub.Btc -> R.drawable.btc
        is CurrencyRub.Cad -> R.drawable.cad
        is CurrencyRub.Chf -> R.drawable.chf
        is CurrencyRub.Cny -> R.drawable.cny
        is CurrencyRub.Eth -> R.drawable.eth
        is CurrencyRub.Eur -> R.drawable.eur
        is CurrencyRub.Gbp -> R.drawable.gbp
        is CurrencyRub.Hkd -> R.drawable.hkd
        is CurrencyRub.Jpy -> R.drawable.jpy
        is CurrencyRub.Usd -> R.drawable.usd
    }
}

private fun getCurrencyAmount(currencyRub: CurrencyRub): Double {
    return when (currencyRub) {
        is CurrencyRub.Aud -> currencyRub.amount
        is CurrencyRub.Btc -> currencyRub.amount
        is CurrencyRub.Cad -> currencyRub.amount
        is CurrencyRub.Chf -> currencyRub.amount
        is CurrencyRub.Cny -> currencyRub.amount
        is CurrencyRub.Eth -> currencyRub.amount
        is CurrencyRub.Eur -> currencyRub.amount
        is CurrencyRub.Gbp -> currencyRub.amount
        is CurrencyRub.Hkd -> currencyRub.amount
        is CurrencyRub.Jpy -> currencyRub.amount
        is CurrencyRub.Usd -> currencyRub.amount
    }
}