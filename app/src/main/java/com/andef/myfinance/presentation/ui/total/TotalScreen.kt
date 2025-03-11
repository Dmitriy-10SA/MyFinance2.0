package com.andef.myfinance.presentation.ui.total

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.presentation.ui.rows.TopRowWithDateAndTotal
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.presentation.viewmodel.total.TotalViewModel
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import java.util.Date

@Composable
fun TotalScreen(
    viewModelFactory: ViewModelFactory,
    startDate: Date,
    endDate: Date,
    paddingValues: PaddingValues
) {
    val viewModel: TotalViewModel = viewModel(factory = viewModelFactory)

    val fullAmountIncome = viewModel.getFullAmountIncome(startDate, endDate).observeAsState()
    val fullAmountExpense = viewModel.getFullAmountExpense(startDate, endDate).observeAsState()

    val selectedItem = remember { mutableStateOf(TotalItem.PieChart as TotalItem) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            val expensesAmount = fullAmountExpense.value ?: 0.0
            val incomesAmount = fullAmountIncome.value ?: 0.0
            val del = incomesAmount - expensesAmount
            TopRowWithDateAndTotal(startDate, endDate, del)
            SegmentedButtonsRow(selectedItem)
        }
        item {
            Spacer(modifier = Modifier.padding(10.dp))
            when (selectedItem.value) {
                TotalItem.BarChart -> {
                    MyFinanceBarChart(
                        modifier = Modifier
                            .size(getScreenWidth().dp)
                            .padding(16.dp),
                        fullAmountIncome.value ?: 0.0,
                        fullAmountExpense.value ?: 0.0
                    )
                }

                TotalItem.PieChart -> {
                    MyFinancePieChart(
                        modifier = Modifier
                            .size(getScreenWidth().dp)
                            .padding(16.dp),
                        fullAmountIncome.value ?: 0.0,
                        fullAmountExpense.value ?: 0.0
                    )
                }
            }
        }
    }
}

@Composable
private fun SegmentedButtonsRow(selectedItem: MutableState<TotalItem>) {
    val items = listOf(TotalItem.PieChart, TotalItem.BarChart)

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        for ((index, item) in items.withIndex()) {
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = items.size,
                    baseShape = RoundedCornerShape(10.dp)
                ),
                onClick = {
                    selectedItem.value = item
                },
                selected = item == selectedItem.value,
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.background,
                    activeContentColor = MaterialTheme.colorScheme.onBackground,
                    activeBorderColor = MaterialTheme.colorScheme.onBackground,
                    inactiveContainerColor = MaterialTheme.colorScheme.background,
                    inactiveContentColor = MaterialTheme.colorScheme.onBackground,
                    inactiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledInactiveContainerColor = MaterialTheme.colorScheme.background,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveContentColor = MaterialTheme.colorScheme.onBackground,
                    disabledInactiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveContainerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(stringResource(item.nameResId))
            }
        }
    }
}

@Composable
private fun MyFinanceBarChart(
    modifier: Modifier,
    fullAmountIncome: Double,
    fullAmountExpense: Double
) {
    BarChart(
        modifier = modifier,
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    fullAmountExpense.toFloat(),
                    if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_red_black)
                    } else {
                        colorResource(R.color.my_red)
                    },
                    stringResource(R.string.expenses)
                ),
                BarChartData.Bar(
                    fullAmountIncome.toFloat(),
                    if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_green_black)
                    } else {
                        colorResource(R.color.my_green)
                    },
                    stringResource(R.string.incomes)
                )
            )
        ),
        animation = simpleChartAnimation(),
        barDrawer = SimpleBarDrawer(),
        xAxisDrawer = SimpleXAxisDrawer(axisLineColor = MaterialTheme.colorScheme.onBackground),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = MaterialTheme.colorScheme.onBackground,
            axisLineColor = MaterialTheme.colorScheme.onBackground
        ),
        labelDrawer = SimpleValueDrawer(labelTextColor = MaterialTheme.colorScheme.onBackground)
    )
}

@Composable
private fun MyFinancePieChart(
    modifier: Modifier,
    fullAmountIncome: Double,
    fullAmountExpense: Double
) {
    val sum = fullAmountExpense + fullAmountIncome
    val greenAmount = (fullAmountIncome / sum).toFloat()
    val redAmount = (fullAmountExpense / sum).toFloat()
    PieChart(
        modifier = modifier,
        pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice(
                    redAmount,
                    if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_red_black)
                    } else {
                        colorResource(R.color.my_red)
                    }
                ),
                PieChartData.Slice(
                    greenAmount,
                    if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_green_black)
                    } else {
                        colorResource(R.color.my_green)
                    }
                )
            )
        ),
        animation = simpleChartAnimation(),
        sliceDrawer = SimpleSliceDrawer()
    )
}

@Composable
private fun getScreenWidth(): Int {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    return screenWidth
}