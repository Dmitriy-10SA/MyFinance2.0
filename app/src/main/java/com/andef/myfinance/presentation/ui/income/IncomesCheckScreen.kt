package com.andef.myfinance.presentation.ui.income

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.presentation.ui.fab.FABForCheckScreen
import com.andef.myfinance.presentation.ui.load.LoadScreen
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
    paddingValues: PaddingValues,
    onIncomeClickListener: (Income) -> Unit,
    onFABClickListener: () -> Unit
) {
    val viewModel: IncomesCheckViewModel = viewModel(factory = viewModelFactory)
    LaunchedEffect(startDate, endDate) {
        viewModel.setDates(startDate, endDate)
    }
    val incomesState = viewModel.state.observeAsState(IncomesState.Initial)
    val amount = remember { mutableDoubleStateOf(0.0) }

    Scaffold(
        modifier = Modifier.padding(paddingValues),
        floatingActionButton = {
            FABForCheckScreen(
                onFABClickListener = onFABClickListener
            )
        }
    ) {
        when (val currentState = incomesState.value) {
            is IncomesState.Amount -> {
                amount.doubleValue = currentState.amount
            }

            IncomesState.Error -> {

            }

            is IncomesState.Incomes -> {
                LazyColumn {
                    items(items = currentState.incomes, key = { it.id }) { income ->
                        IncomeCard(income, onIncomeClickListener)
                    }
                }
            }

            IncomesState.Initial -> {
                LoadScreen()
            }

            IncomesState.Loading -> {
                LoadScreen()
            }
        }

        TopRowWithDateAndTotal(startDate, endDate, amount.doubleValue)
    }
}