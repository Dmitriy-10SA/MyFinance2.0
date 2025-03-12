package com.andef.myfinance.presentation.ui.income

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.presentation.ui.fab.FABForCheckScreen
import com.andef.myfinance.presentation.ui.rows.TopRowWithDateAndTotal
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.presentation.viewmodel.income.IncomesCheckViewModel
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IncomesCheckScreen(
    viewModelFactory: ViewModelFactory,
    startDate: Date,
    endDate: Date,
    isDarkTheme: Boolean,
    paddingValues: PaddingValues,
    onIncomeClickListener: (Income) -> Unit,
    onFABClickListener: () -> Unit
) {
    val viewModel: IncomesCheckViewModel = viewModel(factory = viewModelFactory)
    val incomes = viewModel.getIncomes(startDate, endDate).observeAsState(listOf())
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
        Column(Modifier.fillMaxSize()) {
            TopRowWithDateAndTotal(startDate, endDate, fullAmount.value ?: 0.0)
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items = incomes.value, key = { it.id }) { income ->
                    val dismissState = rememberSwipeToDismissBoxState()

                    LaunchedEffect(dismissState.currentValue) {
                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart ||
                            dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd
                        ) {
                            viewModel.removeIncome(income.id)
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
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
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
                        IncomeCard(income, onIncomeClickListener)
                    }
                }
            }
        }
    }
}