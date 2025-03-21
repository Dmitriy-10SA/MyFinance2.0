package com.andef.myfinance.presentation.ui.income

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.presentation.formatter.DateFormatter
import com.andef.myfinance.presentation.ui.datepicker.MyFinanceDatePicker
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.presentation.viewmodel.factory.ViewModelFactory
import com.andef.myfinance.presentation.viewmodel.income.IncomeViewModel
import java.util.Date

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IncomeScreen(
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory,
    onBackHandlerClickListener: () -> Unit,
    isAddMode: Boolean = true,
    isDarkTheme: Boolean,
    income: com.andef.myfinance.domain.income.entities.Income? = null
) {
    val viewModel: IncomeViewModel = viewModel(factory = viewModelFactory)

    val amount: MutableState<String>
    val comment: MutableState<String>
    val category: MutableState<com.andef.myfinance.domain.income.entities.IncomeCategory>
    val dateState: MutableState<Date>
    if (isAddMode) {
        amount = remember { mutableStateOf("") }
        comment = remember { mutableStateOf("") }
        category = remember { mutableStateOf(com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY) }
        dateState = remember { mutableStateOf(Date()) }
    } else {
        amount = remember { mutableStateOf(income!!.amount.toString()) }
        comment = remember { mutableStateOf(income!!.comment) }
        category = remember { mutableStateOf(income!!.category) }
        dateState = remember { mutableStateOf(income!!.date) }
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
            Scaffold(
                modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
                contentWindowInsets = WindowInsets.ime,
                topBar = {
                    TopBar(onBackHandlerClickListener)
                }
            ) {
                LazyColumn(modifier = Modifier.padding(it)) {
                    item {
                        Spacer(modifier = Modifier.padding(5.dp))
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
                    }
                    item {
                        Spacer(modifier = Modifier.padding(16.dp))
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
                                        viewModel.addIncome(
                                            com.andef.myfinance.domain.income.entities.Income(
                                                amount = amount.value.toDouble(),
                                                category = category.value,
                                                comment = comment.value,
                                                date = dateState.value.toStartOfDay()
                                            )
                                        )
                                    } else {
                                        viewModel.changeIncome(
                                            income = income!!,
                                            newAmount = amount.value.toDouble(),
                                            newCategory = category.value,
                                            newComment = comment.value,
                                            newDate = dateState.value.toStartOfDay()
                                        )
                                    }
                                    onBackHandlerClickListener()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isDarkTheme) {
                                        colorResource(R.color.my_blue)
                                    } else {
                                        colorResource(R.color.my_orange)
                                    },
                                    contentColor = Color.White,
                                    disabledContainerColor = if (isDarkTheme) {
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
                        Spacer(modifier = Modifier.padding(8.dp))
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
                    date = dateState.value.toStartOfDay(1).time,
                    isDarkTheme = isDarkTheme
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
    category: MutableState<com.andef.myfinance.domain.income.entities.IncomeCategory>,
    onCardClickListener: (com.andef.myfinance.domain.income.entities.IncomeCategory) -> Unit,
    isDarkTheme: Boolean
) {
    val items = listOf(
        com.andef.myfinance.domain.income.entities.IncomeCategory.SALARY,
        com.andef.myfinance.domain.income.entities.IncomeCategory.BANK,
        com.andef.myfinance.domain.income.entities.IncomeCategory.LUCK,
        com.andef.myfinance.domain.income.entities.IncomeCategory.GIFTS,
        com.andef.myfinance.domain.income.entities.IncomeCategory.OTHER
    )
    Column {
        IncomesCategoryCardsRow(items, 0, 3, isDarkTheme, category, onCardClickListener)
        Spacer(modifier = Modifier.padding(4.dp))
        IncomesCategoryCardsRow(items, 3, 5, isDarkTheme, category, onCardClickListener)
    }
}

@Composable
private fun IncomesCategoryCardsRow(
    items: List<com.andef.myfinance.domain.income.entities.IncomeCategory>,
    startI: Int,
    endI: Int,
    isDarkTheme: Boolean,
    category: MutableState<com.andef.myfinance.domain.income.entities.IncomeCategory>,
    onCardClickListener: (com.andef.myfinance.domain.income.entities.IncomeCategory) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in startI until endI) {
            IncomeCategoryCard(items, i, isDarkTheme, category, onCardClickListener)
        }
    }
}

@Composable
private fun IncomeCategoryCard(
    items: List<com.andef.myfinance.domain.income.entities.IncomeCategory>,
    i: Int,
    isDarkTheme: Boolean,
    category: MutableState<com.andef.myfinance.domain.income.entities.IncomeCategory>,
    onCardClickListener: (com.andef.myfinance.domain.income.entities.IncomeCategory) -> Unit
) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = {
            onCardClickListener(items[i])
        },
        colors = CardDefaults.cardColors(
            containerColor = if (category.value == items[i]) {
                if (isDarkTheme) {
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
                    text = stringResource(R.string.input_income),
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