package com.andef.myfinance.presentation.ui.currency

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub
import com.andef.myfinance.presentation.viewmodel.currency.CurrencyViewModel
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory

@Composable
fun CurrencyScreen(
    viewModelFactory: ViewModelFactory,
    onBackHandlerClickListener: () -> Unit,
    onNetworkError: () -> Unit
) {
    val viewModel: CurrencyViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.observeAsState(CurrencyState.Initial)

    Scaffold(
        topBar = {
            TopBar(onBackHandlerClickListener)
        }
    ) {
        when (val currentState = state.value) {
            is CurrencyState.Currency -> {
                CurrencyListScreen(currentState.currency, it)
            }

            CurrencyState.Initial -> {
                LoadScreen(it)
            }

            CurrencyState.Loading -> {
                LoadScreen(it)
            }

            CurrencyState.Error -> {
                onNetworkError()
            }
        }
    }
    BackHandler {
        onBackHandlerClickListener()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(onBackHandlerClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontSize = 24.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackHandlerClickListener()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun CurrencyListScreen(currency: List<CurrencyRub>, paddingValues: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        items(items = currency, key = { it.iconResId }) { currency ->
            CurrencyCard(currency)
        }
    }
}

@Composable
private fun LoadScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = Color.Gray,
            trackColor = MaterialTheme.colorScheme.onBackground
        )
    }
}