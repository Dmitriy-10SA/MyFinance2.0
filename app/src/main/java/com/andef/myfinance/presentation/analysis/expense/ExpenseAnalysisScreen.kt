package com.andef.myfinance.presentation.analysis.expense

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.navigation.defaultScreenAnim
import com.andef.myfinance.navigation.rangePickerAnim
import com.andef.myfinance.presentation.analysis.AnalysisItem
import com.andef.myfinance.presentation.analysis.AnalysisSegmentedButtonsRow
import com.andef.myfinance.presentation.analysis.AnalysisSetDate
import com.andef.myfinance.presentation.analysis.AnalysisTopBar
import com.andef.myfinance.presentation.analysis.AnalysisTopBarItem
import com.andef.myfinance.presentation.analysis.CardWithText
import com.andef.myfinance.presentation.picker.MyFinanceRangeDatePicker
import com.andef.myfinance.utils.formatter.AmountFormatter
import com.andef.myfinance.utils.formatter.PercentFormatter
import com.andef.myfinance.utils.ui.ErrorScreen
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.LoadScreen
import com.andef.myfinance.utils.ui.TopRowWithDateAndTotal
import com.andef.myfinance.utils.ui.getExpenseIconResId
import com.andef.myfinance.utils.ui.getExpenseNameResId
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
fun ExpenseAnalysisScreen(
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
            ExpenseAnalysisContent(
                viewModelFactory = viewModelFactory,
                topBarState = topBarState,
                startDate = startDateState.value,
                endDate = endDateState.value,
                selectedItem = selectedItem,
                isDarkTheme = isDarkTheme,
                onBackClickListener = onBackClickListener,
                isRangeDatePickerScreen = isRangeDatePickerScreen
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
private fun ExpenseAnalysisContent(
    viewModelFactory: ViewModelFactory,
    topBarState: MutableState<AnalysisTopBarItem>,
    startDate: Date,
    endDate: Date,
    selectedItem: MutableState<AnalysisItem>,
    isDarkTheme: Boolean,
    isRangeDatePickerScreen: MutableState<Boolean>,
    onBackClickListener: () -> Unit
) {
    val viewModel: ExpenseAnalysisViewModel = viewModel(factory = viewModelFactory)
    val expenses = viewModel.getExpenses(startDate, endDate).collectAsState(listOf())
    val fullAmount = viewModel.getFullAmountExpense(startDate, endDate).collectAsState(0.0)

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
                    text = stringResource(R.string.expenses_analysis),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        if (expenses.value.isEmpty()) {
            IfEmptyScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.wait_expenses)
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
                            DetailExpenseScreenBarChart(
                                paddingValues = paddingValues,
                                viewModel = viewModel,
                                expenses = expenses.value,
                                modifier = Modifier
                                    .size(getScreenWidth().dp)
                                    .padding((getScreenWidth() / 6).dp),
                                isDarkTheme = isDarkTheme
                            )
                        }

                        AnalysisItem.PieChart -> {
                            DetailExpenseScreenPieChart(
                                paddingValues = paddingValues,
                                viewModel = viewModel,
                                expenses = expenses.value,
                                modifier = Modifier
                                    .size(getScreenWidth().dp)
                                    .padding((getScreenWidth() / 6).dp),
                                isDarkTheme = isDarkTheme
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailExpenseScreenPieChart(
    paddingValues: PaddingValues,
    viewModel: ExpenseAnalysisViewModel,
    expenses: List<Expense>,
    modifier: Modifier,
    isDarkTheme: Boolean
) {
    val state =
        viewModel.detailExpensePieChartState.collectAsState(ExpenseAnalysisPieChartState.Initial)
    LaunchedEffect(expenses) {
        viewModel.getExpensesAmountForPieChart(expenses)
    }

    when (val currentState = state.value) {
        is ExpenseAnalysisPieChartState.ExpenseAmountPercent -> {
            val expensesAmountPercent = currentState.expenseAmountPercent
            PieChart(
                modifier = modifier,
                pieChartData = PieChartData(
                    slices = listOf(
                        PieChartData.Slice(
                            expensesAmountPercent[0],
                            if (isDarkTheme) {
                                colorResource(R.color.my_green_black)
                            } else {
                                colorResource(R.color.my_green)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[1],
                            if (isDarkTheme) {
                                colorResource(R.color.my_coral_red)
                            } else {
                                colorResource(R.color.my_coral_red)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[2],
                            if (isDarkTheme) {
                                colorResource(R.color.my_brown_black)
                            } else {
                                colorResource(R.color.my_brown)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[3],
                            if (isDarkTheme) {
                                colorResource(R.color.my_blue)
                            } else {
                                colorResource(R.color.my_blue)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[4],
                            if (isDarkTheme) {
                                colorResource(R.color.my_cyan)
                            } else {
                                colorResource(R.color.my_cyan)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[5],
                            if (isDarkTheme) {
                                colorResource(R.color.my_teal)
                            } else {
                                colorResource(R.color.my_teal)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[6],
                            if (isDarkTheme) {
                                colorResource(R.color.my_gray)
                            } else {
                                colorResource(R.color.my_gray)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[7],
                            if (isDarkTheme) {
                                colorResource(R.color.my_violet)
                            } else {
                                colorResource(R.color.my_violet)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[8],
                            if (isDarkTheme) {
                                colorResource(R.color.my_yellow_black)
                            } else {
                                colorResource(R.color.my_yellow)
                            }
                        ),
                        PieChartData.Slice(
                            expensesAmountPercent[9],
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
            LegendWithPercent(isDarkTheme, expensesAmountPercent)
        }

        ExpenseAnalysisPieChartState.Initial -> {
            LoadScreen(paddingValues)
        }

        ExpenseAnalysisPieChartState.Loading -> {
            LoadScreen(paddingValues)
        }

        ExpenseAnalysisPieChartState.Error -> {
            ErrorScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.unknown_exception),
                iconResId = R.drawable.unknown_exception
            )
        }
    }
}

@Composable
private fun DetailExpenseScreenBarChart(
    paddingValues: PaddingValues,
    viewModel: ExpenseAnalysisViewModel,
    expenses: List<Expense>,
    modifier: Modifier,
    isDarkTheme: Boolean
) {
    val state =
        viewModel.detailExpenseBarChartState.collectAsState(ExpenseAnalysisBarChartState.Initial)
    LaunchedEffect(expenses) { viewModel.getExpenseAmountForBarChart(expenses) }

    when (val currentState = state.value) {
        is ExpenseAnalysisBarChartState.ExpenseAmount -> {
            val expensesAmount = currentState.expenseAmount

            BarChart(
                modifier = modifier,
                barChartData = BarChartData(
                    bars = listOf(
                        BarChartData.Bar(
                            expensesAmount[0].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_green_black)
                            } else {
                                colorResource(R.color.my_green)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[1].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_coral_red)
                            } else {
                                colorResource(R.color.my_coral_red)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[2].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_brown)
                            } else {
                                colorResource(R.color.my_brown)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[3].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_blue)
                            } else {
                                colorResource(R.color.my_blue)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[4].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_cyan)
                            } else {
                                colorResource(R.color.my_cyan)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[5].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_teal)
                            } else {
                                colorResource(R.color.my_teal)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[6].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_gray)
                            } else {
                                colorResource(R.color.my_gray)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[7].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_violet)
                            } else {
                                colorResource(R.color.my_violet)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[8].toFloat(),
                            if (isDarkTheme) {
                                colorResource(R.color.my_yellow_black)
                            } else {
                                colorResource(R.color.my_yellow)
                            },
                            ""
                        ),
                        BarChartData.Bar(
                            expensesAmount[9].toFloat(),
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
            FullInfo(expensesAmount)
        }

        ExpenseAnalysisBarChartState.Initial -> {
            LoadScreen(paddingValues)
        }

        ExpenseAnalysisBarChartState.Loading -> {
            LoadScreen(paddingValues)
        }

        ExpenseAnalysisBarChartState.Error -> {
            ErrorScreen(
                paddingValues = paddingValues,
                text = stringResource(R.string.unknown_exception),
                iconResId = R.drawable.unknown_exception
            )
        }
    }
}

@Composable
private fun FullInfo(expensesAmount: List<Double>) {
    val expenses = mutableListOf<Expense>()
    for (i in expensesAmount.indices) {
        if (expensesAmount[i] != 0.0) {
            expenses.add(
                Expense(
                    amount = expensesAmount[i],
                    category = getCategory(i),
                    comment = "",
                    date = Date()
                )
            )
        }
    }
    Column {
        for (expense in expenses) {
            FullInfoCardOfExpense(expense)
        }
    }
}

private fun getCategory(i: Int): ExpenseCategory {
    return when (i) {
        0 -> ExpenseCategory.PRODUCTS
        1 -> ExpenseCategory.CAFE
        2 -> ExpenseCategory.HOME
        3 -> ExpenseCategory.GIFTS
        4 -> ExpenseCategory.STUDY
        5 -> ExpenseCategory.HEALTH
        6 -> ExpenseCategory.TRANSPORT
        7 -> ExpenseCategory.SPORT
        8 -> ExpenseCategory.CLOTHES
        else -> ExpenseCategory.OTHER
    }
}

@Composable
private fun FullInfoCardOfExpense(expense: Expense) {
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
                painter = painterResource(getExpenseIconResId(expense.category)),
                contentDescription = stringResource(getExpenseNameResId(expense.category))
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(getExpenseNameResId(expense.category)))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = AmountFormatter.format(expense.amount),
                modifier = Modifier.padding(start = 6.dp)
            )
        }
    }
}

@Composable
private fun LegendWithPercent(isDarkTheme: Boolean, expensesAmountPercent: List<Float>) {
    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
        item {
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_green_black),
                lightColor = colorResource(R.color.my_green),
                text = stringResource(R.string.products) +
                        " (${PercentFormatter.format(expensesAmountPercent[0].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_coral_red),
                lightColor = colorResource(R.color.my_coral_red),
                text = stringResource(R.string.cafe) +
                        " (${PercentFormatter.format(expensesAmountPercent[1].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_brown_black),
                lightColor = colorResource(R.color.my_brown),
                text = stringResource(R.string.home) +
                        " (${PercentFormatter.format(expensesAmountPercent[2].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_blue),
                lightColor = colorResource(R.color.my_blue),
                text = stringResource(R.string.gifts) +
                        " (${PercentFormatter.format(expensesAmountPercent[3].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_cyan),
                lightColor = colorResource(R.color.my_cyan),
                text = stringResource(R.string.study) +
                        " (${PercentFormatter.format(expensesAmountPercent[4].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_teal),
                lightColor = colorResource(R.color.my_teal),
                text = stringResource(R.string.health) +
                        " (${PercentFormatter.format(expensesAmountPercent[5].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_gray),
                lightColor = colorResource(R.color.my_gray),
                text = stringResource(R.string.transport) +
                        " (${PercentFormatter.format(expensesAmountPercent[6].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_violet),
                lightColor = colorResource(R.color.my_violet),
                text = stringResource(R.string.sport) +
                        " (${PercentFormatter.format(expensesAmountPercent[7].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_yellow_black),
                lightColor = colorResource(R.color.my_yellow),
                text = stringResource(R.string.clothes) +
                        " (${PercentFormatter.format(expensesAmountPercent[8].toDouble() * 100)})"
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_orange),
                lightColor = colorResource(R.color.my_orange),
                text = stringResource(R.string.other) +
                        " (${PercentFormatter.format(expensesAmountPercent[9].toDouble() * 100)})"
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
                text = stringResource(R.string.products)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_coral_red),
                lightColor = colorResource(R.color.my_coral_red),
                text = stringResource(R.string.cafe)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_brown_black),
                lightColor = colorResource(R.color.my_brown),
                text = stringResource(R.string.home)
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
                darkColor = colorResource(R.color.my_cyan),
                lightColor = colorResource(R.color.my_cyan),
                text = stringResource(R.string.study)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_teal),
                lightColor = colorResource(R.color.my_teal),
                text = stringResource(R.string.health)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_gray),
                lightColor = colorResource(R.color.my_gray),
                text = stringResource(R.string.transport)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_violet),
                lightColor = colorResource(R.color.my_violet),
                text = stringResource(R.string.sport)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            CardWithText(
                isDarkTheme = isDarkTheme,
                darkColor = colorResource(R.color.my_yellow_black),
                lightColor = colorResource(R.color.my_yellow),
                text = stringResource(R.string.clothes)
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