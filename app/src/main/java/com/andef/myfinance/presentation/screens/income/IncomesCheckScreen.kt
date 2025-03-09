package com.andef.myfinance.presentation.screens.income

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.presentation.cards.income.IncomeCard
import com.andef.myfinance.presentation.fabs.FABForCheckScreen
import com.andef.myfinance.presentation.rows.TopRowWithDateAndTotal
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@Composable
fun IncomesCheckScreen(
    incomes: List<Income>,
    startDate: Date,
    endDate: Date,
    fullAmount: Double,
    paddingValues: PaddingValues,
    onIncomeClickListener: (Income) -> Unit,
    onFABClickListener: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FABForCheckScreen {
                onFABClickListener()
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                TopRowWithDateAndTotal(startDate, endDate, fullAmount)
            }
            items(items = incomes, key = { it.id }) { income ->
                IncomeCard(income) {
                    onIncomeClickListener(income)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DarkIncomesCheckScreenTest() {
    MyFinanceTheme(darkTheme = true) {
        IncomesCheckScreen(
            TEST_LIST,
            Date(),
            Date(),
            100.0,
            PaddingValues(8.dp),
            {},
            {}
        )
    }
}

@Preview
@Composable
private fun LightIncomesCheckScreenTest() {
    MyFinanceTheme(darkTheme = false) {
        IncomesCheckScreen(
            TEST_LIST,
            Date(),
            Date(),
            1000.0,
            PaddingValues(8.dp),
            {},
            {}
        )
    }
}

private val TEST_LIST = listOf(
    Income(1, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(2, 10000.0, IncomeCategory.BANK, "", Date()),
    Income(3, 10000.0, IncomeCategory.GIFTS, "", Date()),
    Income(4, 10000.0, IncomeCategory.OTHER, "", Date()),
    Income(5, 10000.0, IncomeCategory.LUCK, "", Date()),
    Income(6, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(7, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(8, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(9, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(10, 10000.0, IncomeCategory.SALARY, "", Date()),
    Income(11, 10000.0, IncomeCategory.SALARY, "", Date()),
)