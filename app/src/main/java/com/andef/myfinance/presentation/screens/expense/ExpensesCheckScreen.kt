package com.andef.myfinance.presentation.screens.expense

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.presentation.cards.expense.ExpenseCard
import com.andef.myfinance.presentation.fabs.FABForCheckScreen
import com.andef.myfinance.presentation.rows.TopRowWithDateAndTotal
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExpensesCheckScreen(
    expenses: List<Expense>,
    startDate: Date,
    endDate: Date,
    fullAmount: Double,
    paddingValues: PaddingValues,
    onExpenseClickListener: (Expense) -> Unit,
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
            items(items = expenses, key = { it.id }) { expense ->
                ExpenseCard(expense) {
                    onExpenseClickListener(expense)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DarkExpensesCheckScreenTest() {
    MyFinanceTheme(darkTheme = true) {
        ExpensesCheckScreen(
            TEST_LIST,
            Date(1710000000000),
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
private fun LightExpensesCheckScreenTest() {
    MyFinanceTheme(darkTheme = false) {
        ExpensesCheckScreen(
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
    Expense(1, 10000.0, ExpenseCategory.PRODUCTS, "", Date()),
    Expense(2, 10000.0, ExpenseCategory.CAFE, "", Date()),
    Expense(3, 10000.0, ExpenseCategory.GIFTS, "", Date()),
    Expense(4, 10000.0, ExpenseCategory.OTHER, "", Date()),
    Expense(5, 10000.0, ExpenseCategory.HEALTH, "", Date()),
    Expense(6, 10000.0, ExpenseCategory.SPORT, "", Date()),
    Expense(7, 10000.0, ExpenseCategory.STUDY, "", Date()),
    Expense(8, 10000.0, ExpenseCategory.CLOTHES, "", Date()),
    Expense(9, 10000.0, ExpenseCategory.TRANSPORT, "", Date()),
    Expense(10, 10000.0, ExpenseCategory.PRODUCTS, "", Date()),
    Expense(11, 10000.0, ExpenseCategory.CAFE, "", Date())
)