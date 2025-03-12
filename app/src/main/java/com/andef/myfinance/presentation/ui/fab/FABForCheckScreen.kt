package com.andef.myfinance.presentation.ui.fab

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.myfinance.R

@Composable
fun FABForCheckScreen(onFABClickListener: () -> Unit, isDarkTheme: Boolean) {
    FloatingActionButton(
        containerColor = if (isDarkTheme) {
            colorResource(R.color.my_blue)
        } else {
            colorResource(R.color.my_orange)
        },
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        onClick = {
            onFABClickListener()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = stringResource(R.string.add_expense)
        )
    }
}