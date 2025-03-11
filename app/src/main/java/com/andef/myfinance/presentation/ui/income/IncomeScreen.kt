package com.andef.myfinance.presentation.ui.income

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.ui.theme.MyFinanceTheme

@Composable
fun IncomeScreen(
    onBackHandlerClickListener: () -> Unit,
    isAddMode: Boolean = true,
    income: Income? = null
) {
    val amount = remember { mutableStateOf("") }
    val category = remember { mutableStateOf(null) }

    Scaffold {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Spacer(modifier = Modifier.padding(10.dp))
                DoubleInputTextForAmount(amount, { number ->
                    number.toDoubleOrNull()?.let {
                        amount.value = number
                    }
                    if (number.isEmpty()
                        || number.startsWith("0")
                        || number.startsWith(".")
                    ) {
                        amount.value = ""
                    }
                })
            }
            item {
                Spacer(modifier = Modifier.padding(14.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.category),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
            item {
                CategoryChoose()
            }
            item {
                Spacer(modifier = Modifier.padding(14.dp))
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = stringResource(R.string.comment),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
    BackHandler {
        onBackHandlerClickListener()
    }
}

@Composable
private fun CategoryChoose() {
    val items = listOf(
        IncomeCategory.SALARY,
        IncomeCategory.BANK,
        IncomeCategory.LUCK,
        IncomeCategory.GIFTS,
        IncomeCategory.OTHER
    )
    Column {
        IncomesCategoryCardsRow(items, 0, 3)
        Spacer(modifier = Modifier.padding(4.dp))
        IncomesCategoryCardsRow(items, 3, 5)
    }
}

@Composable
private fun IncomesCategoryCardsRow(items: List<IncomeCategory>, startI: Int, endI: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in startI until endI) {
            IncomeCategoryCard(items, i)
        }
    }
}

@Composable
private fun IncomeCategoryCard(items: List<IncomeCategory>, i: Int) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {

        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(65.dp),
                painter = painterResource(items[i].iconResId),
                contentDescription = stringResource(items[i].nameResId)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(stringResource(items[i].nameResId))
        }
    }
}

@Composable
private fun DoubleInputTextForAmount(
    amount: MutableState<String>,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .clickable {},
            value = amount.value,
            onValueChange = {
                onValueChanged(it)
            },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            label = {
                Text(
                    text = stringResource(R.string.input_income),
                    fontSize = 20.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            maxLines = 1,

            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview
@Composable
private fun DarkTest() {
    MyFinanceTheme(darkTheme = true) {
        IncomeScreen({})
    }
}

@Preview
@Composable
private fun LightTest() {
    MyFinanceTheme(darkTheme = false) {
        IncomeScreen({})
    }
}