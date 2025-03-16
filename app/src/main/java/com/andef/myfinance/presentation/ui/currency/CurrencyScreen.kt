package com.andef.myfinance.presentation.ui.currency

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.network.currency.entities.CurrencyRub
import com.andef.myfinance.presentation.error.NetworkErrorScreen
import com.andef.myfinance.presentation.utils.toDate
import com.andef.myfinance.presentation.viewmodel.currency.CurrencyViewModel
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import java.time.LocalDate

@Composable
fun CurrencyScreen(
    viewModelFactory: ViewModelFactory,
    onBackHandlerClickListener: () -> Unit,
    isDarkTheme: Boolean
) {
    val viewModel: CurrencyViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.observeAsState(CurrencyState.Initial)
    val topBarState = remember {
        mutableStateOf(CurrencyTopNavigationItem.Today as CurrencyTopNavigationItem)
    }

    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            CurrencyTopNavigationItem.Month -> {
                viewModel.loadCurrency(LocalDate.now().minusMonths(1).toDate())
            }

            CurrencyTopNavigationItem.Today -> {
                viewModel.loadCurrency(LocalDate.now().minusDays(1).toDate())
            }

            CurrencyTopNavigationItem.Week -> {
                viewModel.loadCurrency(LocalDate.now().minusWeeks(1).toDate())
            }

            CurrencyTopNavigationItem.Year -> {
                viewModel.loadCurrency(LocalDate.now().minusYears(1).toDate())
            }
        }
    }

    Scaffold(
        topBar = {
            Column {
                TopOfTopBar(onBackHandlerClickListener)
                LowOfTopBar(topBarState)
            }
        }
    ) {
        when (val currentState = state.value) {
            CurrencyState.Initial -> {
                LoadScreen(it)
            }

            CurrencyState.Loading -> {
                LoadScreen(it)
            }

            CurrencyState.Error -> {
                NetworkErrorScreen(it)
            }

            is CurrencyState.CurrencyFirstPartWithPercent -> {
                CurrencyWithPercentListScreen(currentState.pair, it, isDarkTheme)
            }

            is CurrencyState.CurrencySecondPartWithPercent -> {
                CurrencyWithPercentListScreen(currentState.pair, it, isDarkTheme)
            }

            is CurrencyState.CurrencyThirdPartWithPercent -> {
                CurrencyWithPercentListScreen(currentState.pair, it, isDarkTheme)
            }

            is CurrencyState.CurrencyFourthPartWithPercent -> {
                CurrencyWithPercentListScreen(currentState.pair, it, isDarkTheme, true)
            }
        }
    }
    BackHandler {
        onBackHandlerClickListener()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LowOfTopBar(topBarState: MutableState<CurrencyTopNavigationItem>) {
    val items = listOf(
        CurrencyTopNavigationItem.Today,
        CurrencyTopNavigationItem.Week,
        CurrencyTopNavigationItem.Month,
        CurrencyTopNavigationItem.Year
    )
    PrimaryScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = topBarState.value.index,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(topBarState.value.index)
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 28.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    ) {
        for (item in items) {
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                selected = topBarState.value == item,
                onClick = {
                    topBarState.value = item
                },
                text = {
                    Text(
                        text = stringResource(item.nameResId),
                        fontSize = 15.sp
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopOfTopBar(onBackHandlerClickListener: () -> Unit) {
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
private fun CurrencyWithPercentListScreen(
    currency: List<Pair<CurrencyRub, Double>>,
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    isAll: Boolean = false
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(top = 6.dp)
    ) {
        items(items = currency, key = { it.first.nameResId }) { currency ->
            CurrencyCardWithPercent(currency.first, currency.second, isDarkTheme)
        }
        if (!isAll) {
            item {
                LoadScreen(paddingValues)
            }
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