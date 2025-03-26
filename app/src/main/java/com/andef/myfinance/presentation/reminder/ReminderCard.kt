package com.andef.myfinance.presentation.reminder

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.ui.getExpenseIconResId
import com.andef.myfinance.utils.ui.getExpenseNameResId

@Composable
fun ReminderCard(reminder: Reminder, onReminderClickListener: (Reminder) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(8.dp, shape = RoundedCornerShape(10.dp), ambientColor = MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = {
            onReminderClickListener(reminder)
        }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(getExpenseIconResId(reminder.category)),
                contentDescription = stringResource(getExpenseNameResId(reminder.category))
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(getExpenseNameResId(reminder.category)))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = AmountFormatter.format(reminder.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}