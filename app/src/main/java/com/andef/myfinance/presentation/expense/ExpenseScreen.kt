package com.andef.myfinance.presentation.expense

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
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.entities.ExpenseCategory
import com.andef.myfinance.navigation.defaultScreenAnim
import com.andef.myfinance.navigation.rangePickerAnim
import com.andef.myfinance.presentation.datepicker.MyFinanceDatePicker
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White
import com.andef.myfinance.utils.formatter.DateFormatter
import com.andef.myfinance.utils.ui.toDate
import com.andef.myfinance.utils.ui.DoubleInputTextForAmount
import com.andef.myfinance.utils.ui.ErrorScreen
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.IncomeOrExpenseTopBar
import com.andef.myfinance.utils.ui.TextInputTextForAmount
import com.andef.myfinance.utils.ui.getExpenseIconResId
import com.andef.myfinance.utils.ui.getExpenseNameResId
import java.time.LocalDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(
    isDarkTheme: Boolean,
    viewModelFactory: ViewModelFactory,
    id: Int? = null,
    onBackClickListener: () -> Unit
) {
    val viewModel: ExpenseViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    val firstStart = remember { mutableStateOf(true) }
    val isVisible = remember { mutableStateOf(false) }
    val amount = remember { mutableStateOf("") }
    val comment = remember { mutableStateOf("") }
    val category = remember { mutableStateOf(ExpenseCategory.PRODUCTS) }
    val dateState = remember { mutableStateOf(LocalDate.now().toDate()) }

    val isDatePickerScreen = remember { mutableStateOf(false) }

    LaunchedEffect(id) { viewModel.loadExpense(id) }

    AnimatedContent(
        targetState = isDatePickerScreen.value,
        transitionSpec = {
            if (isDatePickerScreen.value) rangePickerAnim else defaultScreenAnim
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { isDatePicker ->
        if (!isDatePicker) {
            Scaffold(
                contentWindowInsets = WindowInsets.ime,
                topBar = {
                    IncomeOrExpenseTopBar(
                        isDarkTheme = isDarkTheme,
                        onBackClickListener = onBackClickListener,
                        amount = amount,
                        onActionClickListener = {
                            if (id == null) {
                                viewModel.addExpense(
                                    Expense(
                                        amount = amount.value.toDouble(),
                                        category = category.value,
                                        comment = comment.value,
                                        date = dateState.value.toStartOfDay()
                                    )
                                )
                            } else {
                                viewModel.changeExpense(
                                    id = id,
                                    amount = amount.value.toDouble(),
                                    category = category.value,
                                    comment = comment.value,
                                    date = dateState.value.toStartOfDay()
                                )
                            }
                            onBackClickListener()
                        }
                    )
                }
            ) { paddingValues ->
                when (val currentState = state.value) {
                    ExpenseState.Error -> {
                        ErrorScreen(
                            paddingValues = paddingValues,
                            text = stringResource(R.string.unknown_exception),
                            iconResId = R.drawable.unknown_exception
                        )
                    }

                    is ExpenseState.ExpenseLoad -> {
                        if (firstStart.value) {
                            currentState.expense?.let {
                                amount.value = it.amount.toString()
                                comment.value = it.comment
                                category.value = it.category
                                dateState.value = it.date
                            }
                            isVisible.value = true
                            firstStart.value = false
                        }

                        if (isVisible.value) {
                            ExpenseScreenContent(
                                expense = currentState.expense,
                                paddingValues = paddingValues,
                                isDarkTheme = isDarkTheme,
                                amount = amount,
                                comment = comment,
                                dateState = dateState,
                                category = category,
                                viewModel = viewModel,
                                isDatePickerState = isDatePickerScreen,
                                onBackClickListener = onBackClickListener
                            )
                        } else {
                            IfEmptyScreen(
                                paddingValues = paddingValues,
                                text = stringResource(R.string.wait_we_working)
                            )
                        }
                    }

                    ExpenseState.Initial -> {
                        IfEmptyScreen(
                            paddingValues = paddingValues,
                            text = stringResource(R.string.wait_we_working)
                        )
                    }

                    ExpenseState.Loading -> {
                        IfEmptyScreen(
                            paddingValues = paddingValues,
                            text = stringResource(R.string.wait_we_working)
                        )
                    }
                }
            }
        } else {
            Scaffold { paddingValues ->
                MyFinanceDatePicker(
                    paddingValues = paddingValues,
                    onCloseClickListener = {
                        isDatePickerScreen.value = false
                    },
                    onSaveClickListener = { date ->
                        dateState.value = Date(date)
                        isDatePickerScreen.value = false
                    },
                    date = dateState.value.toStartOfDay(1).time,
                    isDarkTheme = isDarkTheme
                )
            }
        }
    }

    BackHandler {
        onBackClickListener()
    }
}

@Composable
private fun ExpenseScreenContent(
    expense: Expense?,
    paddingValues: PaddingValues,
    isDarkTheme: Boolean,
    amount: MutableState<String>,
    comment: MutableState<String>,
    dateState: MutableState<Date>,
    category: MutableState<ExpenseCategory>,
    viewModel: ExpenseViewModel,
    isDatePickerState: MutableState<Boolean>,
    onBackClickListener: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .navigationBarsPadding()
    ) {
        item {
            Spacer(modifier = Modifier.padding(4.dp))
            DoubleInputTextForAmount(amount, { number ->
                number.toDoubleOrNull()?.let {
                    amount.value = number
                }
                if (number.isEmpty()
                    || number.startsWith("0")
                    || number.startsWith(".")
                ) {
                    amount.value = ""
                }
            })
        }
        item {
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.category),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
        item {
            CategoryChoose(
                category = category,
                onCardClickListener = { incomeCategory ->
                    category.value = incomeCategory
                },
                isDarkTheme = isDarkTheme
            )
        }
        item {
            Spacer(modifier = Modifier.padding(14.dp))
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(R.string.comment),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
        item {
            TextInputTextForAmount(
                comment = comment,
                onValueChanged = { text ->
                    comment.value = text
                }
            )
        }
        item {
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        isDatePickerState.value = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    shape = RoundedCornerShape(10.dp),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.onBackground)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(R.string.date_choose),
                        )
                        Spacer(modifier = Modifier.padding(6.dp))
                        Text(
                            text = stringResource(R.string.date_choose_action),
                            fontSize = 20.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "${stringResource(R.string.now_choose)}${DateFormatter.format(dateState.value.toStartOfDay())}",
                    fontSize = 17.sp
                )
            }
            Spacer(modifier = Modifier.padding(12.dp))
        }
    }
}

@Composable
private fun CategoryChoose(
    category: MutableState<ExpenseCategory>,
    onCardClickListener: (ExpenseCategory) -> Unit,
    isDarkTheme: Boolean
) {
    val items = listOf(
        ExpenseCategory.PRODUCTS,
        ExpenseCategory.CAFE,
        ExpenseCategory.HOME,
        ExpenseCategory.GIFTS,
        ExpenseCategory.STUDY,
        ExpenseCategory.HEALTH,
        ExpenseCategory.TRANSPORT,
        ExpenseCategory.SPORT,
        ExpenseCategory.CLOTHES,
        ExpenseCategory.OTHER
    )
    Column {
        ExpenseCategoryCardsRow(items, 0, 3, isDarkTheme, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 3, 6, isDarkTheme, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 6, 9, isDarkTheme, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 9, 10, isDarkTheme, category, onCardClickListener)
    }
}

@Composable
private fun ExpenseCategoryCardsRow(
    items: List<ExpenseCategory>,
    startI: Int,
    endI: Int,
    isDarkTheme: Boolean,
    category: MutableState<ExpenseCategory>,
    onCardClickListener: (ExpenseCategory) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in startI until endI) {
            ExpenseCategoryCard(items, i, isDarkTheme, category, onCardClickListener)
        }
    }
}

@Composable
private fun ExpenseCategoryCard(
    items: List<ExpenseCategory>,
    i: Int,
    isDarkTheme: Boolean,
    category: MutableState<ExpenseCategory>,
    onCardClickListener: (ExpenseCategory) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onCardClickListener(items[i])
        },
        colors = CardDefaults.cardColors(
            containerColor = if (category.value == items[i]) {
                if (isDarkTheme) Blue else Orange
            } else {
                MaterialTheme.colorScheme.background
            },
            contentColor = if (category.value == items[i]) {
                White
            } else {
                MaterialTheme.colorScheme.onBackground
            }
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .width(100.dp)
                .wrapContentHeight()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(65.dp),
                painter = painterResource(getExpenseIconResId(items[i])),
                contentDescription = stringResource(getExpenseNameResId(items[i]))
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(getExpenseNameResId(items[i])),
                fontSize = 12.sp
            )
        }
    }
}