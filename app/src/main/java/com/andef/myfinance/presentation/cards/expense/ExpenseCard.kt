package com.andef.myfinance.presentation.cards.expense

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
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.presentation.formatter.AmountFormatter
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@Composable
fun ExpenseCard(expense: Expense, onExpenseCardClickListener: (Expense) -> Unit) {
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
            onExpenseCardClickListener(expense)
        }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(expense.category.iconResId),
                contentDescription = stringResource(expense.category.nameResId)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(expense.category.nameResId))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = AmountFormatter.format(expense.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Preview
@Composable
private fun DarkExpenseCardTest() {
    MyFinanceTheme(darkTheme = true) {
        ExpenseCard(
            Expense(
                id = 1,
                amount = 10000.0,
                category = ExpenseCategory.PRODUCTS,
                comment = "",
                date = Date()
            ),
            {}
        )
    }
}

@Preview
@Composable
private fun LightExpenseCardTest() {
    MyFinanceTheme(darkTheme = false) {
        ExpenseCard(
            Expense(
                id = 1,
                amount = 10000.0,
                category = ExpenseCategory.PRODUCTS,
                comment = "",
                date = Date()
            ),
            {}
        )
    }
}