package com.andef.myfinance.presentation.analysis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R

@Composable
fun AnalysisTopBar(
    state: MutableState<AnalysisTopBarItem>,
    onBackClickListener: () -> Unit,
    onPeriodItemClickListener: () -> Unit
) {
    Column {
        TopOfAnalysisTopBar(onBackClickListener)
        LowOfAnalysisTopBar(state, onPeriodItemClickListener)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopOfAnalysisTopBar(onBackClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 24.sp) },
        navigationIcon = {
            IconButton(
                onClick = onBackClickListener
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
private fun LowOfAnalysisTopBar(
    state: MutableState<AnalysisTopBarItem>,
    onPeriodItemClickListener: () -> Unit
) {
    val items = listOf(
        AnalysisTopBarItem.Day,
        AnalysisTopBarItem.Week,
        AnalysisTopBarItem.Month,
        AnalysisTopBarItem.Year,
        AnalysisTopBarItem.Period
    )
    PrimaryScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = state.value.index,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(state.value.index)
                    .height(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(horizontal = 28.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    ) {
        for (item in items) {
            Tab(
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground,
                selected = state.value == item,
                onClick = {
                    state.value = item
                    if (item == AnalysisTopBarItem.Period) {
                        onPeriodItemClickListener()
                    }
                },
                text = { Text(text = stringResource(item.titleResId)) }
            )
        }
    }
}