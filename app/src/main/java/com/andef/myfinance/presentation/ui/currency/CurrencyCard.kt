package com.andef.myfinance.presentation.ui.currency

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub
import com.andef.myfinance.domain.network.currency.entities.usd.UsdRub
import com.andef.myfinance.presentation.formatter.AmountFormatter

@Composable
fun CurrencyCard(currencyRub: CurrencyRub) {
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
            Text(
                text = AmountFormatter.format(currencyRub.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Test() {
    CurrencyCard(UsdRub(100.00))
}