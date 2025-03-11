package com.andef.myfinance.presentation.ui.datepicker

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFinanceDatePicker(
    paddingValues: PaddingValues,
    onCloseClickListener: () -> Unit,
    onSaveClickListener: (Long) -> Unit,
    date: Long,
    dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() }
) {
    val state = rememberDatePickerState(initialSelectedDateMillis = date)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    onCloseClickListener()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            TextButton(
                onClick = {
                    onSaveClickListener(state.selectedDateMillis!!)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_blue)
                    } else {
                        colorResource(R.color.my_orange)
                    },
                    disabledContainerColor = if (isSystemInDarkTheme()) {
                        colorResource(R.color.my_blue_with_low_alpha)
                    } else {
                        colorResource(R.color.my_orange_with_low_alpha)
                    },
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                ),
                enabled = state.selectedDateMillis != null
            ) {
                Text(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                    text = stringResource(R.string.save),
                    fontSize = 16.sp
                )
            }
        }
        DatePicker(
            state = state,
            modifier = Modifier.weight(1f),
            dateFormatter = dateFormatter,
            title = {},
            headline = {},
            colors = DatePickerDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onBackground,
                headlineContentColor = MaterialTheme.colorScheme.onBackground,
                weekdayContentColor = MaterialTheme.colorScheme.onBackground,
                subheadContentColor = MaterialTheme.colorScheme.onBackground,
                navigationContentColor = MaterialTheme.colorScheme.onBackground,
                yearContentColor = MaterialTheme.colorScheme.onBackground,
                disabledYearContentColor = MaterialTheme.colorScheme.onBackground,
                currentYearContentColor = MaterialTheme.colorScheme.onBackground,
                selectedYearContentColor = MaterialTheme.colorScheme.onBackground,
                disabledSelectedYearContentColor = MaterialTheme.colorScheme.onBackground,
                selectedYearContainerColor = MaterialTheme.colorScheme.background,
                disabledSelectedYearContainerColor = MaterialTheme.colorScheme.background,
                dayContentColor = MaterialTheme.colorScheme.onBackground,
                disabledDayContentColor = MaterialTheme.colorScheme.onBackground,
                selectedDayContentColor = Color.White,
                disabledSelectedDayContentColor = MaterialTheme.colorScheme.background,
                selectedDayContainerColor = if (isSystemInDarkTheme()) {
                    colorResource(R.color.my_blue)
                } else {
                    colorResource(R.color.my_orange)
                },
                disabledSelectedDayContainerColor = MaterialTheme.colorScheme.background,
                todayContentColor = MaterialTheme.colorScheme.onBackground,
                todayDateBorderColor = MaterialTheme.colorScheme.onBackground,
                dayInSelectionRangeContentColor = Color.White,
                dayInSelectionRangeContainerColor = if (isSystemInDarkTheme()) {
                    colorResource(R.color.my_blue)
                } else {
                    colorResource(R.color.my_orange)
                },
                dividerColor = MaterialTheme.colorScheme.background,
                dateTextFieldColors = TextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onBackground,
                    focusedTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
                    errorTextColor = Color.Red,
                    errorCursorColor = Color.Red,
                    errorSupportingTextColor = Color.Red,
                    errorPrefixColor = Color.Red,
                    errorPlaceholderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorSuffixColor = Color.Red,
                    errorIndicatorColor = Color.Red,
                    focusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                    disabledSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedSupportingTextColor = MaterialTheme.colorScheme.onBackground,
                    focusedPrefixColor = MaterialTheme.colorScheme.onBackground,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                    focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    disabledLabelColor = MaterialTheme.colorScheme.onBackground,
                    disabledSuffixColor = MaterialTheme.colorScheme.onBackground,
                    focusedSuffixColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedSuffixColor = MaterialTheme.colorScheme.onBackground,
                    focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                    disabledIndicatorColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    errorContainerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    cursorColor = MaterialTheme.colorScheme.onBackground,
                    disabledPrefixColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedPrefixColor = MaterialTheme.colorScheme.onBackground
                )
            )
        )
    }
    BackHandler {
        onCloseClickListener()
    }
}