package com.andef.myfinance.utils.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
fun IncomeOrExpenseTopBar(
    amount: MutableState<String>,
    isDarkTheme: Boolean,
    onBackClickListener: () -> Unit,
    onActionClickListener: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground
            ),
        title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp) },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClickListener()
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
        ),
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
                enabled = isFullInfoForAddOrChange(amount),
                onClick = onActionClickListener
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