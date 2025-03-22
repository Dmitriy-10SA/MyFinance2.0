package com.andef.myfinance.presentation.income

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
import androidx.compose.ui.unit.dp
import com.andef.myfinance.R
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.ui.getIncomeIconResId
import com.andef.myfinance.utils.ui.getIncomeNameResId


@Composable
fun IncomeCard(income: Income, onIncomeCardClickListener: (Income) -> Unit) {
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
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground),
        onClick = {
            onIncomeCardClickListener(income)
        }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(getIncomeIconResId(income.category)),
                contentDescription = stringResource(getIncomeNameResId(income.category))
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(getIncomeNameResId(income.category)))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = AmountFormatter.format(income.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}