package com.andef.myfinance.utils.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DoubleInputTextForAmount(
    label: String,
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
                    text = label,
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