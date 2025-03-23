package com.andef.myfinance.presentation.analysis.income


import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.navigation.defaultScreenAnim
import com.andef.myfinance.navigation.rangePickerAnim
import com.andef.myfinance.presentation.analysis.AnalysisItem
import com.andef.myfinance.presentation.analysis.AnalysisSegmentedButtonsRow
import com.andef.myfinance.presentation.analysis.AnalysisSetDate
import com.andef.myfinance.presentation.analysis.AnalysisTopBar
import com.andef.myfinance.presentation.analysis.AnalysisTopBarItem
import com.andef.myfinance.presentation.datepicker.MyFinanceRangeDatePicker
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.formatter.PercentFormatter
import com.andef.myfinance.utils.ui.ErrorScreen
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.LoadScreen
import com.andef.myfinance.utils.ui.TopRowWithDateAndTotal
import com.andef.myfinance.utils.ui.getIncomeIconResId
import com.andef.myfinance.utils.ui.getIncomeNameResId
import com.andef.myfinance.utils.ui.getScreenWidth
import com.andef.myfinance.utils.ui.toDate
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
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeAnalysisScreen(
    isDarkTheme: Boolean,
    viewModelFactory: ViewModelFactory,
    onBackClickListener: () -> Unit
) {
    val isRangeDatePickerScreen = remember { mutableStateOf(false) }
    val topBarState = remember {
        mutableStateOf(AnalysisTopBarItem.Day as AnalysisTopBarItem)
    }

    val startDateState = remember { mutableStateOf(LocalDate.now().toDate()) }
    val endDateState = remember { mutableStateOf(LocalDate.now().toDate()) }

    val selectedItem = remember { mutableStateOf(AnalysisItem.PieChart as AnalysisItem) }

    AnalysisSetDate(topBarState = topBarState, startDate = startDateState, endDate = endDateState)

    AnimatedContent(
        targetState = isRangeDatePickerScreen.value,
        transitionSpec = {
            if (isRangeDatePickerScreen.value) rangePickerAnim else defaultScreenAnim
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { isRangeDatePicker ->
        if (!isRangeDatePicker) {
            IncomeAnalysisContent(
                viewModelFactory = viewModelFactory,
                topBarState = topBarState,
                startDate = startDateState.value,
                endDate = endDateState.value,
                selectedItem = selectedItem,
                isDarkTheme = isDarkTheme,
                isRangeDatePickerScreen = isRangeDatePickerScreen,
                onBackClickListener = onBackClickListener
            )
        } else {
            MyFinanceRangeDatePicker(
                onCloseClickListener = {
                    isRangeDatePickerScreen.value = false
                },
                onSaveClickListener = { start, end ->
                    startDateState.value = Date(start)
                    endDateState.value = Date(end)
                    isRangeDatePickerScreen.value = false
                },
                isDarkTheme = isDarkTheme
            )
        }
    }
    BackHandler {
        onBackClickListener()
    }
}

@Composable
private fun IncomeAnalysisContent(
    viewModelFactory: ViewModelFactory,
    topBarState: MutableState<AnalysisTopBarItem>,
    startDate: Date,
    endDate: Date,
    selectedItem: MutableState<AnalysisItem>,
    isDarkTheme: Boolean,
    isRangeDatePickerScreen: MutableState<Boolean>,
    onBackClickListener: () -> Unit
) {
    val viewModel: IncomeAnalysisViewModel = viewModel(factory = viewModelFactory)
    val incomes = viewModel.getIncomes(startDate, endDate).collectAsState(listOf())
    val fullAmount = viewModel.getFullAmountIncome(startDate, endDate).collectAsState(0.0)

    Scaffold(
        topBar = {
            AnalysisTopBar(
                state = topBarState,
                onBackClickListener = onBackClickListener,
                onPeriodItemClickListener = {
                    isRangeDatePickerScreen.value = true
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        ambientColor = MaterialTheme.colorScheme.onBackground
                    ),
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.income_analysis),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        if (incomes.value.isEmpty()) {
            IfEmptyScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.wait_incomes)
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
                    TopRowWithDateAndTotal(startDate, endDate, fullAmount.value)
                    AnalysisSegmentedButtonsRow(selectedItem)
                }
                item {
                    Spacer(modifier = Modifier.padding(10.dp))
                    when (selectedItem.value) {
                        AnalysisItem.BarChart -> {
                            DetailIncomeScreenBarChart(
                                incomes = incomes.value,
                                modifier = Modifier
                                    .size(getScreenWidth().dp)
                                    .padding((getScreenWidth() / 6).dp),
                                isDarkTheme = isDarkTheme,
                                viewModel = viewModel,
                                paddingValues = paddingValues
                            )
                        }

                        AnalysisItem.PieChart -> {
                            DetailIncomeScreenPieChart(
                                paddingValues = paddingValues,
                                incomes = incomes.value,
                                modifier = Modifier
                                    .size(getScreenWidth().dp)
                                    .padding((getScreenWidth() / 6).dp),
                                isDarkTheme = isDarkTheme,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailIncomeScreenPieChart(
    paddingValues: PaddingValues,
    viewModel: IncomeAnalysisViewModel,
    incomes: List<Income>,
    modifier: Modifier,
    isDarkTheme: Boolean
) {
    val state =
        viewModel.detailIncomePieChartState.collectAsState(IncomeAnalysisPieChartState.Initial)
    LaunchedEffect(incomes) { viewModel.getIncomesAmountForPieChart(incomes) }

    when (val currentState = state.value) {
        is IncomeAnalysisPieChartState.IncomesAmountPercent -> {
            val incomesAmountPercent = currentState.incomesAmountPercent
            PieChart(
                modifier = modifier,
                pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice(
                            incomesAmountPercent[0],
                            if (isDarkTheme) {
                                colorResource(R.color.my_green_black)
                            } else {
                                colorResource(R.color.my_green)
                            }
                        ),
                        PieChartData.Slice(
                            incomesAmountPercent[1],
                            if (isDarkTheme) {
                                colorResource(R.color.my_brown_black)
                            } else {
                                colorResource(R.color.my_brown)
                            }
                        ),
                        PieChartData.Slice(
                            incomesAmountPercent[2],
                            if (isDarkTheme) {
                                colorResource(R.color.my_yellow_black)
                            } else {
                                colorResource(R.color.my_yellow)
                            }
                        ),
                        PieChartData.Slice(
                            incomesAmountPercent[3],
                            if (isDarkTheme) {
                                colorResource(R.color.my_blue)
                            } else {
                                colorResource(R.color.my_blue)
                            }
                        ),
                        PieChartData.Slice(
                            incomesAmountPercent[4],
                            if (isDarkTheme) {
                                colorResource(R.color.my_orange)
                            } else {
                                colorResource(R.color.my_orange)
                            }
                        )
                    )
                ),
                animation = simpleChartAnimation(),
                sliceDrawer = SimpleSliceDrawer()
            )
            LegendWithPercents(isDarkTheme, incomesAmountPercent)
        }

        IncomeAnalysisPieChartState.Initial -> {
            LoadScreen(paddingValues)
        }

        IncomeAnalysisPieChartState.Loading -> {
            LoadScreen(paddingValues)
        }

        IncomeAnalysisPieChartState.Error -> {
            ErrorScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.unknown_exception),
                iconResId = R.drawable.unknown_exception
            )
        }
    }
}

@Composable
private fun LegendWithPercents(isDarkTheme: Boolean, incomesAmountPercent: List<Float>) {
    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
        item {
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_green_black),
                lightColor = colorResource(R.color.my_green),
                text = stringResource(R.string.salary) +
                        " (${PercentFormatter.format(incomesAmountPercent[0].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_brown_black),
                lightColor = colorResource(R.color.my_brown),
                text = stringResource(R.string.bank) +
                        " (${PercentFormatter.format(incomesAmountPercent[1].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_yellow_black),
                lightColor = colorResource(R.color.my_yellow),
                text = stringResource(R.string.luck) +
                        " (${PercentFormatter.format(incomesAmountPercent[2].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_blue),
                lightColor = colorResource(R.color.my_blue),
                text = stringResource(R.string.gifts) +
                        " (${PercentFormatter.format(incomesAmountPercent[3].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_orange),
                lightColor = colorResource(R.color.my_orange),
                text = stringResource(R.string.other) +
                        " (${PercentFormatter.format(incomesAmountPercent[4].toDouble() * 100)})"
            )
        }

    }
}

@Composable
private fun DetailIncomeScreenBarChart(
    paddingValues: PaddingValues,
    viewModel: IncomeAnalysisViewModel,
    incomes: List<Income>,
    modifier: Modifier,
    isDarkTheme: Boolean
) {
    val state =
        viewModel.detailIncomeBarChartState.collectAsState(IncomeAnalysisBarChartState.Initial)
    LaunchedEffect(incomes) {
        viewModel.getIncomesAmountForBarChart(incomes)
    }

    when (val currentState = state.value) {
        is IncomeAnalysisBarChartState.IncomesAmount -> {
            val incomesAmount = currentState.incomesAmount

            BarChart(
                modifier = modifier,
                barChartData = BarChartData(
                    bars = listOf(
                        BarChartData.Bar(
                            incomesAmount[0].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_green_black)
                            } else {
                                colorResource(R.color.my_green)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            incomesAmount[1].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_brown_black)
                            } else {
                                colorResource(R.color.my_brown)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            incomesAmount[2].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_yellow_black)
                            } else {
                                colorResource(R.color.my_yellow)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            incomesAmount[3].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_blue)
                            } else {
                                colorResource(R.color.my_blue)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            incomesAmount[4].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_orange)
                            } else {
                                colorResource(R.color.my_orange)
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
            Legend(isDarkTheme)
            Spacer(modifier = Modifier.padding(8.dp))
            FullInfo(incomesAmount)
        }

        IncomeAnalysisBarChartState.Initial -> {
            LoadScreen(paddingValues = paddingValues)
        }

        IncomeAnalysisBarChartState.Loading -> {
            LoadScreen(paddingValues = paddingValues)
        }

        IncomeAnalysisBarChartState.Error -> {
            ErrorScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.unknown_exception),
                iconResId = R.drawable.unknown_exception
            )
        }
    }
}

@Composable
private fun FullInfo(incomesAmount: List<Double>) {
    val incomes = mutableListOf<Income>()
    for (i in incomesAmount.indices) {
        if (incomesAmount[i] != 0.0) {
            incomes.add(
                Income(
                    amount = incomesAmount[i],
                    category = getCategory(i),
                    comment = "",
                    date = Date()
                )
            )
        }
    }
    Column {
        for (income in incomes) {
            FullInfoCardOfIncome(income)
        }
    }
}

@Composable
private fun FullInfoCardOfIncome(income: Income) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(getIncomeIconResId(income.category)),
                contentDescription = stringResource(getIncomeNameResId(income.category))
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(getIncomeNameResId(income.category)))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = AmountFormatter.format(income.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Composable
private fun Legend(isDarkTheme: Boolean) {
    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
        item {
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_green_black),
                lightColor = colorResource(R.color.my_green),
                text = stringResource(R.string.salary)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_brown_black),
                lightColor = colorResource(R.color.my_brown),
                text = stringResource(R.string.bank)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_yellow_black),
                lightColor = colorResource(R.color.my_yellow),
                text = stringResource(R.string.luck)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_blue),
                lightColor = colorResource(R.color.my_blue),
                text = stringResource(R.string.gifts)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_orange),
                lightColor = colorResource(R.color.my_orange),
                text = stringResource(R.string.other)
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
            width = 4.dp,
            color = if (isDarkTheme) darkColor else lightColor
        )
    ) {
        Text(
            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 12.dp, end = 12.dp),
            text = text,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

private fun getCategory(i: Int): IncomeCategory {
    return when (i) {
        0 -> IncomeCategory.SALARY
        1 -> IncomeCategory.BANK
        2 -> IncomeCategory.LUCK
        3 -> IncomeCategory.GIFTS
        else -> IncomeCategory.OTHER
    }
}