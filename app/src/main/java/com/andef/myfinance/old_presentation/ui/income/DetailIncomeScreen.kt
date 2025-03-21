package com.andef.myfinance.old_presentation.ui.income
//
//import androidx.activity.compose.BackHandler
//import androidx.compose.animation.AnimatedContent
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.animation.slideInHorizontally
//import androidx.compose.animation.slideOutHorizontally
//import androidx.compose.animation.togetherWith
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.andef.myfinance.R
//import com.andef.myfinance.domain.income.entities.Income
//import com.andef.myfinance.domain.income.entities.IncomeCategory
//import com.andef.myfinance.presentation.detail.DetailItem
//import com.andef.myfinance.presentation.detail.DetailScreenState
//import com.andef.myfinance.presentation.detail.DetailSegmentedButtonsRow
//import com.andef.myfinance.presentation.detail.DetailTopBar
//import com.andef.myfinance.presentation.error.UnKnownErrorScreen
//import com.andef.myfinance.presentation.formatter.AmountFormatter
//import com.andef.myfinance.presentation.formatter.PercentFormatter
//import com.andef.myfinance.presentation.ui.datepicker.MyFinanceRangeDatePicker
//import com.andef.myfinance.presentation.ui.main.TopNavigationItem
//import com.andef.myfinance.presentation.ui.rows.TopRowWithDateAndTotal
//import com.andef.myfinance.data.utils.getScreenWidth
//import com.andef.myfinance.data.utils.toDate
//import com.andef.myfinance.ViewModelFactory
//import com.andef.myfinance.presentation.viewmodel.income.DetailIncomeViewModel
//import com.github.tehras.charts.bar.BarChart
//import com.github.tehras.charts.bar.BarChartData
//import com.github.tehras.charts.bar.renderer.bar.SimpleBarDrawer
//import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
//import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
//import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
//import com.github.tehras.charts.piechart.PieChart
//import com.github.tehras.charts.piechart.PieChartData
//import com.github.tehras.charts.piechart.animation.simpleChartAnimation
//import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
//import java.time.LocalDate
//import java.util.Date
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DetailIncomeScreen(
//    viewModelFactory: ViewModelFactory,
//    onBackClickListener: () -> Unit,
//    isDarkTheme: Boolean
//) {
//    val state =
//        remember { mutableStateOf(DetailScreenState.DetailIncomeOrExpenseScreen as DetailScreenState) }
//    val topBarState = remember { mutableStateOf(TopNavigationItem.Today as TopNavigationItem) }
//
//    val startDateState = remember { mutableStateOf(Date()) }
//    val endDateState = remember { mutableStateOf(Date()) }
//
//    val selectedItem = remember { mutableStateOf(DetailItem.PieChart as DetailItem) }
//
//    LaunchedEffect(topBarState.value) {
//        when (topBarState.value) {
//            TopNavigationItem.Month -> {
//                endDateState.value = Date()
//                startDateState.value = LocalDate.now().minusMonths(1).toDate()
//            }
//
//            TopNavigationItem.Period -> {
//                if (state.value != DetailScreenState.DatePickerScreen) {
//                    state.value = DetailScreenState.DatePickerScreen
//                }
//            }
//
//            TopNavigationItem.Today -> {
//                endDateState.value = Date()
//                startDateState.value = Date()
//            }
//
//            TopNavigationItem.Week -> {
//                endDateState.value = Date()
//                startDateState.value = LocalDate.now().minusWeeks(1).toDate()
//            }
//
//            TopNavigationItem.Year -> {
//                endDateState.value = Date()
//                startDateState.value = LocalDate.now().minusYears(1).toDate()
//            }
//        }
//    }
//
//    AnimatedContent(
//        targetState = state.value,
//        transitionSpec = {
//            if (targetState is DetailScreenState.DatePickerScreen) {
//                (slideInHorizontally { it } + fadeIn())
//                    .togetherWith(slideOutHorizontally { -it } + fadeOut())
//            } else {
//                (slideInHorizontally { -it } + fadeIn())
//                    .togetherWith(slideOutHorizontally { it } + fadeOut())
//            }
//        },
//        modifier = Modifier.background(MaterialTheme.colorScheme.background)
//    ) { screenState ->
//        when (screenState) {
//            DetailScreenState.DatePickerScreen -> {
//                Scaffold {
//                    MyFinanceRangeDatePicker(
//                        paddingValues = it,
//                        onCloseClickListener = {
//                            state.value = DetailScreenState.DetailIncomeOrExpenseScreen
//                        },
//                        onSaveClickListener = { start, end ->
//                            endDateState.value = Date(end)
//                            startDateState.value = Date(start)
//                            state.value = DetailScreenState.DetailIncomeOrExpenseScreen
//                        },
//                        isDarkTheme = isDarkTheme
//                    )
//                }
//            }
//
//            DetailScreenState.DetailIncomeOrExpenseScreen -> {
//                DetailIncomeScreenContent(
//                    viewModelFactory = viewModelFactory,
//                    topBarState = topBarState,
//                    state = state,
//                    startDate = startDateState.value,
//                    endDate = endDateState.value,
//                    onBackClickListener = onBackClickListener,
//                    isDarkTheme = isDarkTheme,
//                    selectedItem = selectedItem
//                )
//            }
//        }
//    }
//
//    BackHandler {
//        onBackClickListener()
//    }
//}
//
//@Composable
//private fun DetailIncomeScreenContent(
//    viewModelFactory: ViewModelFactory,
//    topBarState: MutableState<TopNavigationItem>,
//    startDate: Date,
//    endDate: Date,
//    selectedItem: MutableState<DetailItem>,
//    isDarkTheme: Boolean,
//    state: MutableState<DetailScreenState>,
//    onBackClickListener: () -> Unit
//) {
//    val viewModel: DetailIncomeViewModel = viewModel(factory = viewModelFactory)
//    val incomes = viewModel.getIncomes(startDate, endDate).observeAsState(listOf())
//    val fullAmount = viewModel.getFullAmountIncome(startDate, endDate).observeAsState()
//
//    Scaffold(
//        topBar = {
//            DetailTopBar(
//                state = topBarState,
//                onBackClickListener = onBackClickListener,
//                onPeriodItemClickListener = {
//                    state.value = DetailScreenState.DatePickerScreen
//                }
//            )
//        },
//        bottomBar = {
//            BottomAppBar(
//                modifier = Modifier.shadow(16.dp),
//                containerColor = MaterialTheme.colorScheme.background,
//                contentColor = MaterialTheme.colorScheme.onBackground
//            ) {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    text = stringResource(R.string.income_analisis),
//                    fontSize = 24.sp,
//                    textAlign = TextAlign.Center
//                )
//            }
//        }
//    ) {
//        if (incomes.value.isEmpty()) {
//            WaitScreen(it)
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it)
//                    .padding(bottom = 8.dp),
//                verticalArrangement = Arrangement.Top,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                item {
//                    TopRowWithDateAndTotal(startDate, endDate, fullAmount.value ?: 0.0)
//                    DetailSegmentedButtonsRow(selectedItem)
//                }
//                item {
//                    Spacer(modifier = Modifier.padding(10.dp))
//                    when (selectedItem.value) {
//                        DetailItem.BarChart -> {
//                            DetailIncomeScreenBarChart(
//                                incomes = incomes.value,
//                                modifier = Modifier
//                                    .size(com.andef.myfinance.data.utils.getScreenWidth().dp)
//                                    .padding((com.andef.myfinance.data.utils.getScreenWidth() / 6).dp),
//                                isDarkTheme = isDarkTheme,
//                                viewModel = viewModel,
//                                paddingValues = it
//                            )
//                        }
//
//                        DetailItem.PieChart -> {
//                            DetailIncomeScreenPieChart(
//                                paddingValues = it,
//                                incomes = incomes.value,
//                                modifier = Modifier
//                                    .size(com.andef.myfinance.data.utils.getScreenWidth().dp)
//                                    .padding((com.andef.myfinance.data.utils.getScreenWidth() / 6).dp),
//                                isDarkTheme = isDarkTheme,
//                                viewModel = viewModel
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun WaitScreen(paddingValues: PaddingValues) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(paddingValues)
//            .padding(start = 12.dp, end = 12.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(R.drawable.watch),
//            contentDescription = stringResource(R.string.watch),
//        )
//        Spacer(modifier = Modifier.padding(12.dp))
//        Text(
//            text = stringResource(R.string.wait_incomes),
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//    }
//}
//
//@Composable
//private fun DetailIncomeScreenPieChart(
//    paddingValues: PaddingValues,
//    viewModel: DetailIncomeViewModel,
//    incomes: List<com.andef.myfinance.domain.income.entities.Income>,
//    modifier: Modifier,
//    isDarkTheme: Boolean
//) {
//    val state =
//        viewModel.detailIncomePieChartState.observeAsState(DetailIncomePieChartState.Initial)
//    LaunchedEffect(incomes) {
//        viewModel.getIncomesAmountForPieChart(incomes)
//    }
//
//    when (val currentState = state.value) {
//        is DetailIncomePieChartState.IncomesAmount -> {
//            val incomesAmountPercent = currentState.incomesAmountPercent
//            PieChart(
//                modifier = modifier,
//                pieChartData = PieChartData(
//                    slices = listOf(
//                        PieChartData.Slice(
//                            incomesAmountPercent[0],
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_green_black)
//                            } else {
//                                colorResource(R.color.my_green)
//                            }
//                        ),
//                        PieChartData.Slice(
//                            incomesAmountPercent[1],
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_brown_black)
//                            } else {
//                                colorResource(R.color.my_brown)
//                            }
//                        ),
//                        PieChartData.Slice(
//                            incomesAmountPercent[2],
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_yellow_black)
//                            } else {
//                                colorResource(R.color.my_yellow)
//                            }
//                        ),
//                        PieChartData.Slice(
//                            incomesAmountPercent[3],
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_blue)
//                            } else {
//                                colorResource(R.color.my_blue)
//                            }
//                        ),
//                        PieChartData.Slice(
//                            incomesAmountPercent[4],
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_orange)
//                            } else {
//                                colorResource(R.color.my_orange)
//                            }
//                        )
//                    )
//                ),
//                animation = simpleChartAnimation(),
//                sliceDrawer = SimpleSliceDrawer()
//            )
//            LegendWithPercents(isDarkTheme, incomesAmountPercent)
//        }
//
//        DetailIncomePieChartState.Initial -> {
//            LoadScreen(paddingValues)
//        }
//
//        DetailIncomePieChartState.Loading -> {
//            LoadScreen(paddingValues)
//        }
//
//        DetailIncomePieChartState.Error -> {
//            ErrorScreen(paddingValues)
//        }
//    }
//}
//
//@Composable
//private fun DetailIncomeScreenBarChart(
//    paddingValues: PaddingValues,
//    viewModel: DetailIncomeViewModel,
//    incomes: List<com.andef.myfinance.domain.income.entities.Income>,
//    modifier: Modifier,
//    isDarkTheme: Boolean
//) {
//    val state =
//        viewModel.detailIncomeBarChartState.observeAsState(DetailIncomeBarChartState.Initial)
//    LaunchedEffect(incomes) {
//        viewModel.getIncomesAmountForBarChart(incomes)
//    }
//
//    when (val currentState = state.value) {
//        is DetailIncomeBarChartState.IncomesAmount -> {
//            val incomesAmount = currentState.incomesAmount
//
//            BarChart(
//                modifier = modifier,
//                barChartData = BarChartData(
//                    bars = listOf(
//                        BarChartData.Bar(
//                            incomesAmount[0].toFloat(),
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_green_black)
//                            } else {
//                                colorResource(R.color.my_green)
//                            },
//                            ""
//                        ),
//                        BarChartData.Bar(
//                            incomesAmount[1].toFloat(),
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_brown_black)
//                            } else {
//                                colorResource(R.color.my_brown)
//                            },
//                            ""
//                        ),
//                        BarChartData.Bar(
//                            incomesAmount[2].toFloat(),
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_yellow_black)
//                            } else {
//                                colorResource(R.color.my_yellow)
//                            },
//                            ""
//                        ),
//                        BarChartData.Bar(
//                            incomesAmount[3].toFloat(),
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_blue)
//                            } else {
//                                colorResource(R.color.my_blue)
//                            },
//                            ""
//                        ),
//                        BarChartData.Bar(
//                            incomesAmount[4].toFloat(),
//                            if (isDarkTheme) {
//                                colorResource(R.color.my_orange)
//                            } else {
//                                colorResource(R.color.my_orange)
//                            },
//                            ""
//                        )
//                    )
//                ),
//                animation = simpleChartAnimation(),
//                barDrawer = SimpleBarDrawer(),
//                xAxisDrawer = SimpleXAxisDrawer(axisLineColor = MaterialTheme.colorScheme.onBackground),
//                yAxisDrawer = SimpleYAxisDrawer(
//                    labelTextColor = MaterialTheme.colorScheme.onBackground,
//                    axisLineColor = MaterialTheme.colorScheme.onBackground
//                ),
//                labelDrawer = SimpleValueDrawer(labelTextColor = MaterialTheme.colorScheme.onBackground)
//            )
//            Legend(isDarkTheme)
//            Spacer(modifier = Modifier.padding(8.dp))
//            FullInfo(incomesAmount)
//        }
//
//        DetailIncomeBarChartState.Initial -> {
//            LoadScreen(paddingValues = paddingValues)
//        }
//
//        DetailIncomeBarChartState.Loading -> {
//            LoadScreen(paddingValues = paddingValues)
//        }
//
//        DetailIncomeBarChartState.Error -> {
//            ErrorScreen(paddingValues = paddingValues)
//        }
//    }
//}
//
//@Composable
//private fun FullInfo(incomesAmount: List<Double>) {
//    val incomes = mutableListOf<com.andef.myfinance.domain.income.entities.Income>()
//    for (i in incomesAmount.indices) {
//        if (incomesAmount[i] != 0.0) {
//            incomes.add(
//                com.andef.myfinance.domain.income.entities.Income(
//                    amount = incomesAmount[i],
//                    category = getCategory(i),
//                    comment = "",
//                    date = Date()
//                )
//            )
//        }
//    }
//    Column {
//        for (income in incomes) {
//            FullInfoCardOfExpense(income)
//        }
//    }
//}
//
//private fun getCategory(i: Int): com.andef.myfinance.domain.income.entities.IncomeCategory {
//    return if (i == 0) {
//        com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY
//    } else if (i == 1) {
//        com.andef.myfinance.domain.income.entities.IncomeCategory.BANK
//    } else if (i == 2) {
//        com.andef.myfinance.domain.income.entities.IncomeCategory.LUCK
//    } else if (i == 3) {
//        com.andef.myfinance.domain.income.entities.IncomeCategory.GIFTS
//    } else {
//        com.andef.myfinance.domain.income.entities.IncomeCategory.OTHER
//    }
//}
//
//@Composable
//private fun FullInfoCardOfExpense(income: com.andef.myfinance.domain.income.entities.Income) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        colors = CardDefaults.cardColors(
//            contentColor = MaterialTheme.colorScheme.onBackground,
//            containerColor = MaterialTheme.colorScheme.background
//        ),
//        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.cardElevation(8.dp),
//        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground)
//    ) {
//        Row(
//            modifier = Modifier.padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center
//        ) {
//            Image(
//                painter = painterResource(income.category.iconResId),
//                contentDescription = stringResource(income.category.nameResId)
//            )
//            Spacer(modifier = Modifier.padding(6.dp))
//            Text(text = stringResource(income.category.nameResId))
//            Spacer(modifier = Modifier.weight(1f))
//            Text(
//                text = AmountFormatter.format(income.amount),
//                modifier = Modifier.padding(start = 6.dp)
//            )
//        }
//    }
//}
//
//@Composable
//private fun ErrorScreen(paddingValues: PaddingValues) {
//    UnKnownErrorScreen(paddingValues)
//}
//
//@Composable
//private fun LegendWithPercents(isDarkTheme: Boolean, incomesAmountPercent: List<Float>) {
//    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
//        item {
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_green_black),
//                lightColor = colorResource(R.color.my_green),
//                text = stringResource(R.string.salary) +
//                        " (${PercentFormatter.format(incomesAmountPercent[0].toDouble() * 100)})"
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_brown_black),
//                lightColor = colorResource(R.color.my_brown),
//                text = stringResource(R.string.bank) +
//                        " (${PercentFormatter.format(incomesAmountPercent[1].toDouble() * 100)})"
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_yellow_black),
//                lightColor = colorResource(R.color.my_yellow),
//                text = stringResource(R.string.luck) +
//                        " (${PercentFormatter.format(incomesAmountPercent[2].toDouble() * 100)})"
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_blue),
//                lightColor = colorResource(R.color.my_blue),
//                text = stringResource(R.string.gifts) +
//                        " (${PercentFormatter.format(incomesAmountPercent[3].toDouble() * 100)})"
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_orange),
//                lightColor = colorResource(R.color.my_orange),
//                text = stringResource(R.string.other) +
//                        " (${PercentFormatter.format(incomesAmountPercent[4].toDouble() * 100)})"
//            )
//        }
//
//    }
//}
//
//@Composable
//private fun Legend(isDarkTheme: Boolean) {
//    LazyRow(modifier = Modifier.padding(start = 1.dp, end = 1.dp)) {
//        item {
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_green_black),
//                lightColor = colorResource(R.color.my_green),
//                text = stringResource(R.string.salary)
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_brown_black),
//                lightColor = colorResource(R.color.my_brown),
//                text = stringResource(R.string.bank)
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_yellow_black),
//                lightColor = colorResource(R.color.my_yellow),
//                text = stringResource(R.string.luck)
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_blue),
//                lightColor = colorResource(R.color.my_blue),
//                text = stringResource(R.string.gifts)
//            )
//            Spacer(modifier = Modifier.padding(5.dp))
//            CardWithText(
//                isDarkTheme = isDarkTheme,
//                darkColor = colorResource(R.color.my_orange),
//                lightColor = colorResource(R.color.my_orange),
//                text = stringResource(R.string.other)
//            )
//        }
//
//    }
//}
//
//@Composable
//private fun LoadScreen(paddingValues: PaddingValues) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(paddingValues),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        CircularProgressIndicator(
//            color = Color.Gray,
//            trackColor = MaterialTheme.colorScheme.onBackground
//        )
//    }
//}
//
//@Composable
//private fun CardWithText(
//    isDarkTheme: Boolean,
//    darkColor: Color,
//    lightColor: Color,
//    text: String
//) {
//    Card(
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.background,
//            contentColor = MaterialTheme.colorScheme.onBackground
//        ),
//        border = BorderStroke(
//            4.dp,
//            if (isDarkTheme) {
//                darkColor
//            } else {
//                lightColor
//            }
//        )
//    ) {
//        Text(
//            modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, start = 12.dp, end = 12.dp),
//            text = text,
//            color = MaterialTheme.colorScheme.onBackground
//        )
//    }
//}