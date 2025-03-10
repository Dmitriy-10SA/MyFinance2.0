package com.andef.myfinance.presentation.ui.income

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.presentation.ui.fabs.FABForCheckScreen
import com.andef.myfinance.presentation.ui.rows.TopRowWithDateAndTotal
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.presentation.viewmodel.income.IncomesCheckViewModel
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IncomesCheckScreen(
    viewModelFactory: ViewModelFactory,
    startDate: Date,
    endDate: Date,
    paddingValues: PaddingValues,
    onIncomeClickListener: (Income) -> Unit,
    onFABClickListener: () -> Unit
) {
    val viewModel: IncomesCheckViewModel = viewModel(factory = viewModelFactory)
    LaunchedEffect(startDate, endDate) {
        viewModel.setDates(startDate, endDate)
    }
    val incomesState = viewModel.incomes.observeAsState(IncomesState.Initial)

    Scaffold(
        floatingActionButton = {
            FABForCheckScreen {
                onFABClickListener()
            }
        }
    ) {
        when(val currentState = incomesState.value) {
            IncomesState.Error -> {
                //
            }
            is IncomesState.Incomes -> {
//                LazyColumn(
//                    modifier = Modifier.padding(paddingValues)
//                ) {
//                    item {
//                        TopRowWithDateAndTotal(startDate, endDate, fullAmount)
//                    }
//                    items(items = currentState.incomes, key = { it.id }) { income ->
//                        IncomeCard(income) {
//                            onIncomeClickListener(income)
//                        }
//                    }
//                }
            }
            IncomesState.Initial -> {
                LoadScreen(paddingValues)
            }
            IncomesState.Loading -> {
                LoadScreen(paddingValues)
            }
        }
    }
}

@Composable
private fun LoadScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            trackColor = MaterialTheme.colorScheme.onBackground,
            color = Color.Gray
        )
    }
}