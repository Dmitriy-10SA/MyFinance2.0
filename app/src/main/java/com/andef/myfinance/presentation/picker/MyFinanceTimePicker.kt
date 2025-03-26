package com.andef.myfinance.presentation.picker

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFinanceTimePicker(
    onCloseClickListener: () -> Unit,
    onSaveClickListener: (Long) -> Unit,
    isDarkTheme: Boolean,
) {
    val timePickerState = TimePickerState(0, 0, true)

    Scaffold(
        topBar = {
            TopNavigationAndActionRow(
                isDarkTheme = isDarkTheme,
                state = timePickerState,
                onCloseClickListener = onCloseClickListener,
                onSaveClickListener = onSaveClickListener
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(

                )
            )
        }
        BackHandler {
            onCloseClickListener()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopNavigationAndActionRow(
    isDarkTheme: Boolean,
    state: TimePickerState,
    onCloseClickListener: () -> Unit,
    onSaveClickListener: (Long) -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground
            ),
        title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp) },
        navigationIcon = {
            IconButton(onClick = onCloseClickListener) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = if (isDarkTheme) Blue else Orange,
                    contentColor = White,
                    disabledContainerColor = if (isDarkTheme) {
                        Blue.copy(alpha = 0.3f)
                    } else {
                        Orange.copy(alpha = 0.3f)
                    },
                    disabledContentColor = White
                ),
                onClick = {
                    onSaveClickListener((state.hour * 60L + state.minute) * 60 * 1000)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint = White,
                    contentDescription = stringResource(R.string.confirm_date_choose)
                )
            }
        }
    )
}