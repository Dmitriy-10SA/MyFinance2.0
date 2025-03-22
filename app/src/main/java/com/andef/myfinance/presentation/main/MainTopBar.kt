package com.andef.myfinance.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.andef.myfinance.R
import com.andef.myfinance.navigation.NavigationState
import com.andef.myfinance.navigation.Screen
import com.andef.myfinance.utils.getCurrentDestination
import com.andef.myfinance.utils.isInHierarchy

@Composable
fun MainTopBar(
    topBarState: MutableState<MainTopNavigationItem>,
    navigationState: NavigationState,
    onPeriodTabClickListener: () -> Unit,
    onNavigationMenuIconClickListener: () -> Unit
) {
    val currentDestination = getCurrentDestination(navigationState = navigationState)

    if (isInHierarchy(currentDestination, Screen.MainScreen.route)) {
        Column {
            TitleAndNavigationIconRow(
                title = stringResource(R.string.app_name),
                navigationIcon = Icons.Default.Menu,
                navIconContentDescription = stringResource(R.string.menu),
                onNavigationMenuIconClickListener = onNavigationMenuIconClickListener
            )
            TabsForChooseStartAndEndDate(
                topBarState = topBarState,
                onPeriodTabClickListener = onPeriodTabClickListener
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleAndNavigationIconRow(
    title: String,
    navigationIcon: ImageVector,
    navIconContentDescription: String,
    onNavigationMenuIconClickListener: () -> Unit
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigationMenuIconClickListener) {
                Icon(
                    imageVector = navigationIcon,
                    contentDescription = navIconContentDescription
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TabsForChooseStartAndEndDate(
    topBarState: MutableState<MainTopNavigationItem>,
    onPeriodTabClickListener: () -> Unit
) {
    val items = listOf(
        MainTopNavigationItem.Today,
        MainTopNavigationItem.Week,
        MainTopNavigationItem.Month,
        MainTopNavigationItem.Year,
        MainTopNavigationItem.Period
    )

    PrimaryScrollableTabRow(
        edgePadding = 0.dp,
        selectedTabIndex = topBarState.value.index,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(topBarState.value.index)
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
                selected = topBarState.value == item,
                onClick = {
                    topBarState.value = item
                    if (item == MainTopNavigationItem.Period) {
                        onPeriodTabClickListener()
                    }
                },
                text = {
                    Text(text = stringResource(item.nameResId))
                }
            )
        }
    }
}