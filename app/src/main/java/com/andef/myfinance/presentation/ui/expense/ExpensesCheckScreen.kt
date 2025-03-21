package com.andef.myfinance.presentation.ui.expense

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.presentation.ui.fab.FABForCheckScreen
import com.andef.myfinance.presentation.ui.rows.TopRowWithDateAndTotal
import com.andef.myfinance.presentation.viewmodel.expense.ExpensesCheckViewModel
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExpensesCheckScreen(
    viewModelFactory: ViewModelFactory,
    startDate: Date,
    endDate: Date,
    isDarkTheme: Boolean,
    paddingValues: PaddingValues,
    onExpenseClickListener: (com.andef.myfinance.domain.expense.entities.Expense) -> Unit,
    onFABClickListener: () -> Unit
) {
    val viewModel: ExpensesCheckViewModel = viewModel(factory = viewModelFactory)
    val expenses = viewModel.getExpenses(startDate, endDate).observeAsState(listOf())
    val fullAmount = viewModel.getFullAmount(startDate, endDate).observeAsState()

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        floatingActionButton = {
            FABForCheckScreen(
                onFABClickListener = onFABClickListener,
                isDarkTheme = isDarkTheme
            )
        }
    ) {
        if (expenses.value.isEmpty()) {
            LoadScreen(it)
        } else {
            Column(Modifier.fillMaxSize()) {
                TopRowWithDateAndTotal(startDate, endDate, fullAmount.value ?: 0.0)
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
                            ExpenseCard(expense, onExpenseClickListener)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.watch),
            contentDescription = stringResource(R.string.watch),
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = stringResource(R.string.wait_expenses),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}