package com.andef.myfinance.presentation.total

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.formatter.PercentFormatter
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.TopRowWithDateAndTotal
import com.andef.myfinance.utils.ui.getScreenWidth
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
fun TotalsScreen(
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory,
    isDarkTheme: Boolean,
    startDate: Date,
    endDate: Date
) {
    val viewModel: TotalsScreenViewModel = viewModel(factory = viewModelFactory)
    val fullIncomeAmount = viewModel.getFullIncomeAmount(startDate, endDate).collectAsState(0.0)
    val fullExpenseAmount = viewModel.getFullExpenseAmount(startDate, endDate).collectAsState(0.0)

    val selectedItem = remember { mutableStateOf(TotalItem.PieChart as TotalItem) }

    if (fullIncomeAmount.value == 0.0 && fullExpenseAmount.value == 0.0) {
        IfEmptyScreen(
            paddingValues = paddingValues,
            text = stringResource(R.string.wait_incomes_and_expenses)
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(bottom = 8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val incomesAmount = fullIncomeAmount.value
                val expensesAmount = fullExpenseAmount.value
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
                                .padding((getScreenWidth() / 6).dp),
                            isDarkTheme,
                            fullIncomeAmount.value,
                            fullExpenseAmount.value
                        )
                    }

                    TotalItem.PieChart -> {
                        MyFinancePieChart(
                            modifier = Modifier
                                .size(getScreenWidth().dp)
                                .padding(16.dp),
                            isDarkTheme,
                            fullIncomeAmount.value,
                            fullExpenseAmount.value
                        )
                    }
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
    isDarkTheme: Boolean,
    fullAmountIncome: Double,
    fullAmountExpense: Double
) {
    BarChart(
        modifier = modifier,
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    fullAmountIncome.toFloat(),
                    if (isDarkTheme) {
                        colorResource(R.color.my_green_black)
                    } else {
                        colorResource(R.color.my_green)
                    },
                    ""
                ),
                BarChartData.Bar(
                    fullAmountExpense.toFloat(),
                    if (isDarkTheme) {
                        colorResource(R.color.my_red_black)
                    } else {
                        colorResource(R.color.my_red)
                    },
                    ""
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
    Legend(isDarkTheme, fullAmountIncome, fullAmountExpense)
}

@Composable
private fun MyFinancePieChart(
    modifier: Modifier,
    isDarkTheme: Boolean,
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
                    if (isDarkTheme) {
                        colorResource(R.color.my_red_black)
                    } else {
                        colorResource(R.color.my_red)
                    }
                ),
                PieChartData.Slice(
                    greenAmount,
                    if (isDarkTheme) {
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
    LegendWithPercents(isDarkTheme, incomePercent = greenAmount, expensePercent = redAmount)
}

@Composable
private fun LegendWithPercents(isDarkTheme: Boolean, incomePercent: Float, expensePercent: Float) {
    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
        item {
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_green_black),
                lightColor = colorResource(R.color.my_green),
                text = stringResource(R.string.incomes)
                        + " (${PercentFormatter.format(incomePercent.toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_red_black),
                lightColor = colorResource(R.color.my_red),
                text = stringResource(R.string.expenses)
                        + " (${PercentFormatter.format(expensePercent.toDouble() * 100)})"
            )
        }
    }
}

@Composable
private fun Legend(isDarkTheme: Boolean, incomes: Double, expenses: Double) {
    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
        item {
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_green_black),
                lightColor = colorResource(R.color.my_green),
                text = stringResource(R.string.incomes) + " (${AmountFormatter.format(incomes)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_red_black),
                lightColor = colorResource(R.color.my_red),
                text = stringResource(R.string.expenses) + " (${AmountFormatter.format(expenses)})"
            )
        }
    }
}

@Composable
private fun CardWithText(
    isDarkTheme: Boolean,
    darkColor: Color,
    lightColor: Color,
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        border = BorderStroke(
            4.dp,
            if (isDarkTheme) {
                darkColor
            } else {
                lightColor
            }
        )
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 12.dp, end = 12.dp),
            text = text,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}