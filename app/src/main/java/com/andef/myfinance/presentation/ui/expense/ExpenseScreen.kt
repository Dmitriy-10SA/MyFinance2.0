package com.andef.myfinance.presentation.ui.expense

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.database.expense.entities.Expense
import com.andef.myfinance.domain.database.expense.entities.ExpenseCategory
import com.andef.myfinance.presentation.ui.datepicker.MyFinanceDatePicker
import com.andef.myfinance.presentation.utils.toStartOfDay
import com.andef.myfinance.presentation.viewmodel.expense.ExpenseViewModel
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.ui.theme.MyFinanceTheme
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(
    viewModelFactory: ViewModelFactory,
    onBackHandlerClickListener: () -> Unit,
    isAddMode: Boolean = true,
    expense: Expense? = null
) {
    val viewModel: ExpenseViewModel = viewModel(factory = viewModelFactory)

    val amount: MutableState<String>
    val comment: MutableState<String>
    val category: MutableState<ExpenseCategory>
    val dateState: MutableState<Date>
    if (isAddMode) {
        amount = remember { mutableStateOf("") }
        comment = remember { mutableStateOf("") }
        category = remember { mutableStateOf(ExpenseCategory.PRODUCTS as ExpenseCategory) }
        dateState = remember { mutableStateOf(Date()) }
    } else {
        amount = remember { mutableStateOf(expense!!.amount.toString()) }
        comment = remember { mutableStateOf(expense!!.comment) }
        category = remember { mutableStateOf(expense!!.category as ExpenseCategory) }
        dateState = remember { mutableStateOf(expense!!.date) }
    }

    val isDatePickerState = remember { mutableStateOf(false) }

    AnimatedContent(
        targetState = isDatePickerState.value,
        transitionSpec = {
            if (isDatePickerState.value) {
                (slideInVertically { it } + fadeIn())
                    .togetherWith(slideOutVertically { -it } + fadeOut())
            } else {
                (slideInVertically { -it } + fadeIn())
                    .togetherWith(slideOutVertically { it } + fadeOut())
            }
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) { isDatePickerScreen ->
        if (!isDatePickerScreen) {
            Scaffold(contentWindowInsets = WindowInsets.ime) {
                LazyColumn(modifier = Modifier
                    .padding(it)
                    .padding(top = 16.dp)) {
                    item {
                        Spacer(modifier = Modifier.padding(16.dp))
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
                            }
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
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.padding(10.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .padding(8.dp),
                                enabled = isFullInfoForAddOrChange(amount),
                                onClick = {
                                    if (isAddMode) {
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
                                            expense = expense!!,
                                            newAmount = amount.value.toDouble(),
                                            newCategory = category.value,
                                            newComment = comment.value,
                                            newDate = dateState.value.toStartOfDay()
                                        )
                                    }
                                    onBackHandlerClickListener()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSystemInDarkTheme()) {
                                        colorResource(R.color.my_blue)
                                    } else {
                                        colorResource(R.color.my_orange)
                                    },
                                    contentColor = Color.White,
                                    disabledContainerColor = if (isSystemInDarkTheme()) {
                                        colorResource(R.color.my_blue_with_low_alpha)
                                    } else {
                                        colorResource(R.color.my_orange_with_low_alpha)
                                    },
                                    disabledContentColor = MaterialTheme.colorScheme.onBackground
                                )
                            ) {
                                Text(
                                    text = stringResource(R.string.save),
                                    fontSize = 24.sp,
                                    color = Color.White,
                                    modifier = Modifier.padding(
                                        top = 6.dp,
                                        bottom = 6.dp,
                                        start = 14.dp,
                                        end = 14.dp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Scaffold {
                MyFinanceDatePicker(
                    paddingValues = it,
                    onCloseClickListener = {
                        isDatePickerState.value = false
                    },
                    onSaveClickListener = { date ->
                        dateState.value = Date(date)
                        isDatePickerState.value = false
                    },
                    date = dateState.value.toStartOfDay(1).time
                )
            }
        }
    }
    BackHandler {
        onBackHandlerClickListener()
    }
}

private fun isFullInfoForAddOrChange(amount: MutableState<String>): Boolean {
    return amount.value.isNotEmpty()
}

@Composable
private fun CategoryChoose(
    category: MutableState<ExpenseCategory>,
    onCardClickListener: (ExpenseCategory) -> Unit
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
        ExpenseCategoryCardsRow(items, 0, 3, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 3, 6, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 6, 9, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        ExpenseCategoryCardsRow(items, 9, 10, category, onCardClickListener)
    }
}

@Composable
private fun ExpenseCategoryCardsRow(
    items: List<ExpenseCategory>,
    startI: Int,
    endI: Int,
    category: MutableState<ExpenseCategory>,
    onCardClickListener: (ExpenseCategory) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in startI until endI) {
            ExpenseCategoryCard(items, i, category, onCardClickListener)
        }
    }
}

@Composable
private fun ExpenseCategoryCard(
    items: List<ExpenseCategory>,
    i: Int,
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
                if (isSystemInDarkTheme()) {
                    colorResource(R.color.my_blue)
                } else {
                    colorResource(R.color.my_orange)
                }
            } else {
                MaterialTheme.colorScheme.background
            },
            contentColor = if (category.value == items[i]) {
                Color.White
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
                painter = painterResource(items[i].iconResId),
                contentDescription = stringResource(items[i].nameResId)
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = stringResource(items[i].nameResId),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun DoubleInputTextForAmount(
    amount: MutableState<String>,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = amount.value,
            onValueChange = {
                onValueChanged(it)
            },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            label = {
                Text(
                    text = stringResource(R.string.input_expense),
                    fontSize = 20.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            maxLines = 1,

            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Composable
private fun TextInputTextForAmount(
    comment: MutableState<String>,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            value = comment.value,
            onValueChange = {
                onValueChanged(it)
            },
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
            label = {
                Text(
                    text = stringResource(R.string.input_comment),
                    fontSize = 20.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                disabledSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@Preview
@Composable
private fun DarkTest() {
    MyFinanceTheme(darkTheme = true) {
        ExpenseScreen(ViewModelFactory(mapOf()), {})
    }
}

@Preview
@Composable
private fun LightTest() {
    MyFinanceTheme(darkTheme = false) {
        ExpenseScreen(ViewModelFactory(mapOf()), {})
    }
}