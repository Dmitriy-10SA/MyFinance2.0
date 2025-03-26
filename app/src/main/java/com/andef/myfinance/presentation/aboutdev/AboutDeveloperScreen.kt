package com.andef.myfinance.presentation.aboutdev

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.myfinance.R
import com.andef.myfinance.presentation.reminder.ReminderTopBar

@Composable
fun AboutDeveloperScreen(
    onVKClickListener: () -> Unit,
    onTelegramClickListener: () -> Unit,
    onMailClickListener: () -> Unit,
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
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                contentDescription = stringResource(R.string.ruble_icon)
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.hello_i_am_dmitriy),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(2.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.write_me),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ImageInBox(
                    onClickListener = onVKClickListener,
                    painterResource(R.drawable.vk),
                    stringResource(R.string.vk_icon)
                )
                Spacer(modifier = Modifier.padding(14.dp))
                ImageInBox(
                    onClickListener = onTelegramClickListener,
                    painterResource(R.drawable.telegram),
                    stringResource(R.string.telegram_icon),
                    isTelegram = true
                )
                Spacer(modifier = Modifier.padding(14.dp))
                ImageInBox(
                    onClickListener = onMailClickListener,
                    painterResource(R.drawable.mail),
                    stringResource(R.string.mail_icon),
                )
            }
        }
    }
    BackHandler { onCancelClickListener() }
}

@Composable
private fun ImageInBox(
    onClickListener: () -> Unit,
    painter: Painter,
    contentDescription: String,
    isTelegram: Boolean = false
) {
    Box(
        modifier = Modifier
            .size(55.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.my_blue))
            .clickable { onClickListener() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(if (isTelegram) 45.dp else 50.dp)
                .clip(CircleShape)
                .background(colorResource(R.color.my_blue)),
            painter = painter,
            contentDescription = contentDescription
        )
    }
}