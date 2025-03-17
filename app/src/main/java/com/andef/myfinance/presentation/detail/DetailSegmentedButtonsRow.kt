package com.andef.myfinance.presentation.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun DetailSegmentedButtonsRow(selectedItem: MutableState<DetailItem>) {
    val items = listOf(DetailItem.PieChart, DetailItem.BarChart)

    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        for ((index, item) in items.withIndex()) {
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = items.size,
                    baseShape = RoundedCornerShape(10.dp)
                ),
                onClick = {
                    selectedItem.value = item
                },
                selected = item == selectedItem.value,
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.background,
                    activeContentColor = MaterialTheme.colorScheme.onBackground,
                    activeBorderColor = MaterialTheme.colorScheme.onBackground,
                    inactiveContainerColor = MaterialTheme.colorScheme.background,
                    inactiveContentColor = MaterialTheme.colorScheme.onBackground,
                    inactiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledInactiveContainerColor = MaterialTheme.colorScheme.background,
                    disabledInactiveContentColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveContentColor = MaterialTheme.colorScheme.onBackground,
                    disabledInactiveBorderColor = MaterialTheme.colorScheme.onBackground,
                    disabledActiveContainerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(stringResource(item.nameResId))
            }
        }
    }
}