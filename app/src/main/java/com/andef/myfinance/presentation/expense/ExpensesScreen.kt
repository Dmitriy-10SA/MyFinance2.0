package com.andef.myfinance.presentation.expense

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.TopRowWithDateAndTotal
import java.util.Date

@Composable
fun ExpensesScreen(
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory,
    startDate: Date,
    endDate: Date,
    onExpenseClickListener: (Expense) -> Unit
) {
    val viewModel: ExpensesScreenViewModel = viewModel(factory = viewModelFactory)
    val expenses = viewModel.getExpenseList(startDate, endDate).collectAsState(listOf())
    val fullAmount = viewModel.getFullAmount(startDate, endDate).collectAsState(0.0)

    if (expenses.value.isEmpty()) {
        IfEmptyScreen(paddingValues = paddingValues, text = stringResource(R.string.wait_expenses))
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TopRowWithDateAndTotal(
                startDate = startDate,
                endDate = endDate,
                fullAmount = fullAmount.value
            )
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items = expenses.value, key = { it.id }) { expense ->
                    val dismissState = rememberSwipeToDismissBoxState()

                    LaunchedEffect(dismissState.currentValue) {
                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart ||
                            dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd
                        ) {
                            viewModel.removeExpense(expense.id)
                        }
                    }

                    SwipeToDismissBox(
                        modifier = Modifier.animateItem(),
                        state = dismissState,
                        backgroundContent = {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.onBackground
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Red,
                                    contentColor = MaterialTheme.colorScheme.onBackground
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(start = 5.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.sign_remove)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier.padding(end = 5.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.sign_remove)
                                    )
                                }
                            }
                        }
                    ) {
                        ExpenseCard(
                            expense = expense,
                            onExpenseCardClickListener = onExpenseClickListener
                        )
                    }
                }
            }
        }
    }
}