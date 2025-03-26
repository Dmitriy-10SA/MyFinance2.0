package com.andef.myfinance.presentation.reminder

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White

@Composable
fun NotGrantedReminderListScreen(
    isDarkTheme: Boolean,
    onAcceptClickListener: () -> Unit,
    onCancelClickListener: () -> Unit
) {
    Scaffold(
        topBar = {
            ReminderTopBar(onCancelClickListener = onCancelClickListener)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 12.dp, end = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.notification),
                contentDescription = stringResource(R.string.icon_for_exception),
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(R.string.permission_for_notification_not_accept),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NotGrantedTextButton(
                    modifier = Modifier.weight(1f),
                    isDarkTheme = isDarkTheme,
                    text = stringResource(R.string.back),
                    onButtonClickListener = onCancelClickListener
                )
                Spacer(modifier = Modifier.padding(12.dp))
                NotGrantedTextButton(
                    modifier = Modifier.weight(1f),
                    isDarkTheme = isDarkTheme,
                    text = stringResource(R.string.accept),
                    onButtonClickListener = onAcceptClickListener
                )
            }
        }
    }
    BackHandler { onCancelClickListener() }
}

@Composable
private fun NotGrantedTextButton(
    modifier: Modifier,
    isDarkTheme: Boolean,
    text: String,
    onButtonClickListener: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = onButtonClickListener,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isDarkTheme) Blue else Orange
        )
    ) {
        Text(text = text, color = White, fontSize = 20.sp)
    }
}