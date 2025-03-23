package com.andef.myfinance.presentation.currency

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.currency.entities.CurrencyRub
import com.andef.myfinance.utils.ui.LoadScreen
import com.andef.myfinance.utils.ui.toDate
import java.time.LocalDate

@Composable
fun CurrencyScreen(
    isDarkTheme: Boolean,
    viewModelFactory: ViewModelFactory,
    onBackClickListener: () -> Unit
) {
    val viewModel: CurrencyViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()
    val topBarState = remember {
        mutableStateOf(CurrencyTopBarItem.Day as CurrencyTopBarItem)
    }

    Load(viewModel = viewModel, topBarState = topBarState)

    Scaffold(
        topBar = {
            CurrencyTopBar(topBarState = topBarState, onBackClickListener = onBackClickListener)
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
        onBackClickListener()
    }
}

@Composable
private fun NetworkErrorScreen(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.network_error),
            contentDescription = stringResource(R.string.dead_smile),
        )
        Spacer(modifier = Modifier.padding(12.dp))
        Text(
            text = stringResource(R.string.check_ethernet),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun CurrencyTopBar(
    topBarState: MutableState<CurrencyTopBarItem>,
    onBackClickListener: () -> Unit
) {
    Column {
        TitleAndMenuIconButtonOfTopBar(onBackClickListener = onBackClickListener)
        DateTabsOfTopBar(topBarState = topBarState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleAndMenuIconButtonOfTopBar(onBackClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp) },
        navigationIcon = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                onClick = onBackClickListener
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateTabsOfTopBar(topBarState: MutableState<CurrencyTopBarItem>) {
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
        for (item in currencyTopBarItems) {
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                selected = topBarState.value == item,
                onClick = {
                    topBarState.value = item
                },
                text = { Text(text = stringResource(item.titleResId)) }
            )
        }
    }
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
        items(items = currency, key = { getCurrencyId(it.first) }) { currency ->
            CurrencyCardWithPercent(currency.first, currency.second, isDarkTheme)
        }
        if (!isAll) {
            item {
                LoadScreen(paddingValues)
            }
        } else {
            item {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = stringResource(R.string.reload_one_time_in_day),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun Load(viewModel: CurrencyViewModel, topBarState: MutableState<CurrencyTopBarItem>) {
    LaunchedEffect(topBarState.value) {
        when (topBarState.value) {
            CurrencyTopBarItem.Month -> {
                viewModel.loadCurrency(LocalDate.now().minusMonths(1).toDate())
            }

            CurrencyTopBarItem.Day -> {
                viewModel.loadCurrency(LocalDate.now().minusDays(1).toDate())
            }

            CurrencyTopBarItem.Week -> {
                viewModel.loadCurrency(LocalDate.now().minusWeeks(1).toDate())
            }

            CurrencyTopBarItem.Year -> {
                viewModel.loadCurrency(LocalDate.now().minusYears(1).toDate())
            }

            CurrencyTopBarItem.HalfYear -> {
                viewModel.loadCurrency(LocalDate.now().minusMonths(6).toDate())
            }
        }
    }
}

private val currencyTopBarItems = listOf(
    CurrencyTopBarItem.Day,
    CurrencyTopBarItem.Week,
    CurrencyTopBarItem.Month,
    CurrencyTopBarItem.HalfYear,
    CurrencyTopBarItem.Year
)

private fun getCurrencyId(currencyRub: CurrencyRub): Int {
    return when (currencyRub) {
        is CurrencyRub.Aud -> currencyRub.id
        is CurrencyRub.Btc -> currencyRub.id
        is CurrencyRub.Cad -> currencyRub.id
        is CurrencyRub.Chf -> currencyRub.id
        is CurrencyRub.Cny -> currencyRub.id
        is CurrencyRub.Eth -> currencyRub.id
        is CurrencyRub.Eur -> currencyRub.id
        is CurrencyRub.Gbp -> currencyRub.id
        is CurrencyRub.Hkd -> currencyRub.id
        is CurrencyRub.Jpy -> currencyRub.id
        is CurrencyRub.Usd -> currencyRub.id
    }
}