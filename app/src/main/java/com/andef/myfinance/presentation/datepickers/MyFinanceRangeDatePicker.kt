package com.andef.myfinance.presentation.datepickers

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.andef.myfinance.R
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFinanceRangeDatePicker(
    isDarkTheme: Boolean,
    onCloseClickListener: () -> Unit,
    onActionDateRangePickerIconClickListener: (Long, Long) -> Unit
) {
    val state = rememberDateRangePickerState()
    val dateFormatter: DatePickerFormatter = remember { DatePickerDefaults.dateFormatter() }

    Scaffold(
        topBar = {
            RangeDatePickerTopBar(
                isDarkTheme = isDarkTheme,
                state = state,
                onNavigationDateRangePickerIconClickListener = onCloseClickListener,
                onActionDateRangePickerIconClickListener = onActionDateRangePickerIconClickListener
            )
        }
    ) { paddingValues ->
        DateRangePicker(
            modifier = Modifier.padding(paddingValues),
            state = state,
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
                selectedDayContainerColor = if (isDarkTheme) Blue else Orange,
                disabledSelectedDayContainerColor = MaterialTheme.colorScheme.background,
                todayContentColor = MaterialTheme.colorScheme.onBackground,
                todayDateBorderColor = MaterialTheme.colorScheme.onBackground,
                dayInSelectionRangeContentColor = Color.White,
                dayInSelectionRangeContainerColor = if (isDarkTheme) Blue else Orange,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RangeDatePickerTopBar(
    isDarkTheme: Boolean,
    state: DateRangePickerState,
    onNavigationDateRangePickerIconClickListener: () -> Unit,
    onActionDateRangePickerIconClickListener: (Long, Long) -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onNavigationDateRangePickerIconClickListener) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        },
        actions = {
            IconButton(
                enabled = state.selectedEndDateMillis != null,
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isDarkTheme) Blue else Orange,
                    contentColor = White,
                    disabledContainerColor = if (isDarkTheme) {
                        Blue.copy(alpha = 0.2f)
                    } else {
                        Orange.copy(alpha = 0.2f)
                    },
                    disabledContentColor = White
                ),
                onClick = {
                    onActionDateRangePickerIconClickListener(
                        state.selectedStartDateMillis!!,
                        state.selectedEndDateMillis!!
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.save)
                )
            }
        }
    )
}