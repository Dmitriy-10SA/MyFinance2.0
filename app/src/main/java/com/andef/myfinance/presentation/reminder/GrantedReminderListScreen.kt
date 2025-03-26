package com.andef.myfinance.presentation.reminder

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andef.myfinance.R
import com.andef.myfinance.ViewModelFactory
import com.andef.myfinance.data.utils.toStartOfDay
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.reminder.entities.Reminder
import com.andef.myfinance.ui.theme.Blue
import com.andef.myfinance.ui.theme.Orange
import com.andef.myfinance.ui.theme.White
import com.andef.myfinance.utils.ui.IfEmptyScreen
import com.andef.myfinance.utils.ui.toDate
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.nextMonth
import com.kizitonwose.calendar.core.previousMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth

@Composable
fun GrantedReminderListScreen(
    viewModelFactory: ViewModelFactory,
    isDarkTheme: Boolean,
    onFABClickListener: () -> Unit,
    onReminderClickListener: (Reminder) -> Unit,
    onCancelClickListener: () -> Unit,
    onRemove: (Int) -> Unit
) {
    val viewModel: ReminderListViewModel = viewModel(factory = viewModelFactory)
    val allReminders = viewModel.allReminders.collectAsState(listOf())
    val reminders = viewModel.reminders.collectAsState(listOf())

    val calendarState = rememberCalendarState(
        firstVisibleMonth = YearMonth.now(),
        startMonth = YearMonth.now().minusYears(1),
        endMonth = YearMonth.now().plusYears(2),
        outDateStyle = OutDateStyle.EndOfRow
    )
    val currentYearMonth = remember { mutableStateOf(YearMonth.now()) }
    val currentSelectedDate = remember { mutableStateOf(LocalDate.now()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(currentSelectedDate.value) {
        viewModel.setReminders(currentSelectedDate.value.toDate())
    }

    Scaffold(
        topBar = {
            ReminderTopBar(
                onCancelClickListener = onCancelClickListener
            )
        },
        floatingActionButton = {
            FAB(
                isDarkTheme = isDarkTheme,
                onFABClickListener = onFABClickListener
            )
        }
    ) { paddingValues ->
        Content(
            paddingValues = paddingValues,
            currentYearMonth = currentYearMonth,
            scope = scope,
            calendarState = calendarState,
            currentSelectedDate = currentSelectedDate,
            onReminderClickListener = onReminderClickListener,
            allReminders = allReminders,
            isDarkTheme = isDarkTheme,
            reminders = reminders,
            viewModel = viewModel,
            onRemove = onRemove
        )
    }
    BackHandler { onCancelClickListener() }
}

@Composable
private fun Content(
    paddingValues: PaddingValues,
    currentYearMonth: MutableState<YearMonth>,
    scope: CoroutineScope,
    calendarState: CalendarState,
    currentSelectedDate: MutableState<LocalDate>,
    onReminderClickListener: (Reminder) -> Unit,
    allReminders: State<List<Reminder>>,
    reminders: State<List<Reminder>>,
    isDarkTheme: Boolean,
    viewModel: ReminderListViewModel,
    onRemove: (Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        CalendarCard(
            currentYearMonth = currentYearMonth,
            scope = scope,
            calendarState = calendarState,
            currentSelectedDate = currentSelectedDate,
            isDarkTheme = isDarkTheme,
            allReminders = allReminders
        )
        RemindersCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 6.dp)
                .shadow(
                    elevation = 8.dp,
                    RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
                    ambientColor = MaterialTheme.colorScheme.onBackground
                ),
            onReminderClickListener = onReminderClickListener,
            reminders = reminders,
            viewModel = viewModel,
            onRemove = onRemove,
            isDarkTheme = isDarkTheme
        )
    }
}

@Composable
private fun FAB(isDarkTheme: Boolean, onFABClickListener: () -> Unit) {
    FloatingActionButton(
        containerColor = if (isDarkTheme) Blue else Orange,
        contentColor = White,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(8.dp),
        onClick = onFABClickListener
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = White,
            contentDescription = stringResource(R.string.button_for_add)
        )
    }
}

@Composable
private fun RemindersCard(
    isDarkTheme: Boolean,
    viewModel: ReminderListViewModel,
    modifier: Modifier,
    reminders: State<List<Reminder>>,
    onReminderClickListener: (Reminder) -> Unit,
    onRemove: (Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        val showDialog = remember { mutableStateOf(false) }

        if (reminders.value.isEmpty()) {
            IfEmptyScreen(
                paddingValues = PaddingValues(start = 12.dp, end = 12.dp),
                text = stringResource(R.string.wait_reminders)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp))
                    .padding(top = 4.dp, start = 4.dp, end = 4.dp)
            ) {
                items(items = reminders.value, key = { it.id }) { reminder ->
                    val dismissState = rememberSwipeToDismissBoxState()

                    LaunchedEffect(dismissState.currentValue) {
                        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart ||
                            dismissState.currentValue == SwipeToDismissBoxValue.StartToEnd
                        ) {
                            showDialog.value = true
                        }
                    }

                    if (showDialog.value) {
                        AddInExpenseDialog(
                            isDarkTheme = isDarkTheme,
                            onNoClickListener = {
                                showDialog.value = false
                                viewModel.removeReminder(reminder.id)
                                onRemove(reminder.id)
                            },
                            onYesClickListener = {
                                showDialog.value = false
                                viewModel.removeReminder(reminder.id)
                                onRemove(reminder.id)
                                viewModel.addExpense(
                                    Expense(
                                        id = reminder.id,
                                        amount = reminder.amount,
                                        category = reminder.category,
                                        comment = reminder.text,
                                        date = reminder.time.toStartOfDay()
                                    )
                                )
                            }
                        )
                    }

                    SwipeToDismissBox(
                        modifier = Modifier.animateItem(),
                        state = dismissState,
                        backgroundContent = {
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                border = BorderStroke(
                                    1.dp,
                                    MaterialTheme.colorScheme.onBackground
                                ),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Red,
                                    contentColor = MaterialTheme.colorScheme.onBackground
                                )
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(start = 5.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.sign_remove)
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        modifier = Modifier.padding(end = 5.dp),
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.sign_remove)
                                    )
                                }
                            }
                        }
                    ) {
                        ReminderCard(
                            reminder = reminder,
                            onReminderClickListener = onReminderClickListener
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddInExpenseDialog(
    isDarkTheme: Boolean,
    onNoClickListener: () -> Unit,
    onYesClickListener: () -> Unit
) {
    BasicAlertDialog(onDismissRequest = {}) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(20.dp))
                Text(
                    text = stringResource(R.string.add_in_expenses),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDarkTheme) Blue else Orange
                        ),
                        onClick = onNoClickListener
                    ) {
                        Text(stringResource(R.string.no), fontSize = 20.sp, color = White)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = onYesClickListener,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isDarkTheme) Blue else Orange
                        ),
                    ) {
                        Text(stringResource(R.string.yes), fontSize = 20.sp, color = White)
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
private fun CalendarCard(
    isDarkTheme: Boolean,
    allReminders: State<List<Reminder>>,
    currentYearMonth: MutableState<YearMonth>,
    scope: CoroutineScope,
    calendarState: CalendarState,
    currentSelectedDate: MutableState<LocalDate>
) {
    Card(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 8.dp)
            .shadow(
                8.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = MaterialTheme.colorScheme.onBackground
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 12.dp)) {
            NavButtons(
                currentYearMonth = currentYearMonth,
                onBackClickListener = {
                    scope.launch {
                        calendarState.animateScrollToMonth(
                            calendarState.lastVisibleMonth.yearMonth.previousMonth
                        )
                        currentYearMonth.value =
                            calendarState.lastVisibleMonth.yearMonth
                    }
                },
                onForwardClickListener = {
                    scope.launch {
                        calendarState.animateScrollToMonth(
                            calendarState.lastVisibleMonth.yearMonth.nextMonth
                        )
                        currentYearMonth.value =
                            calendarState.lastVisibleMonth.yearMonth
                    }
                }
            )
            Spacer(modifier = Modifier.padding(2.dp))
            DaysOfWeekTitle(daysOfWeek = daysOfWeek)
            HorizontalCalendar(
                modifier = Modifier
                    .animateContentSize(tween(800))
                    .padding(start = 12.dp, end = 12.dp),
                calendarScrollPaged = false,
                userScrollEnabled = false,
                state = calendarState,
                dayContent = { day ->
                    Day(
                        day = day,
                        currentSelectedDate = currentSelectedDate,
                        onDayClickListener = { calendarDay ->
                            currentSelectedDate.value = calendarDay.date
                        },
                        isDarkTheme = isDarkTheme,
                        allReminders = allReminders
                    )
                }
            )
        }
    }
}

@Composable
private fun NavButtons(
    currentYearMonth: State<YearMonth>,
    onBackClickListener: () -> Unit,
    onForwardClickListener: () -> Unit
) {
    val text = "${
        when (currentYearMonth.value.month) {
            Month.JANUARY -> stringResource(R.string.january_short)
            Month.FEBRUARY -> stringResource(R.string.february_short)
            Month.MARCH -> stringResource(R.string.march_short)
            Month.APRIL -> stringResource(R.string.april_short)
            Month.MAY -> stringResource(R.string.may_short)
            Month.JUNE -> stringResource(R.string.june_short)
            Month.JULY -> stringResource(R.string.july_short)
            Month.AUGUST -> stringResource(R.string.august_short)
            Month.SEPTEMBER -> stringResource(R.string.september_short)
            Month.OCTOBER -> stringResource(R.string.october_short)
            Month.NOVEMBER -> stringResource(R.string.november_short)
            Month.DECEMBER -> stringResource(R.string.december_short)
            else -> null
        }
    } ${currentYearMonth.value.year}${stringResource(R.string.year_short)}"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            text = text,
            textAlign = TextAlign.Start
        )
        IconButton(
            onClick = onBackClickListener
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = null
            )
        }
        IconButton(
            onClick = onForwardClickListener
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<DayOfWeek>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp)
    ) {
        for (dayOfWeek in daysOfWeek) {
            val text = when (dayOfWeek) {
                DayOfWeek.MONDAY -> stringResource(R.string.monday_short)
                DayOfWeek.TUESDAY -> stringResource(R.string.tuesday_short)
                DayOfWeek.WEDNESDAY -> stringResource(R.string.wednesday_short)
                DayOfWeek.THURSDAY -> stringResource(R.string.thursday_short)
                DayOfWeek.FRIDAY -> stringResource(R.string.friday_short)
                DayOfWeek.SATURDAY -> stringResource(R.string.saturday_short)
                DayOfWeek.SUNDAY -> stringResource(R.string.sunday_short)
            }
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = text
            )
        }
    }
}

@Composable
fun Day(
    isDarkTheme: Boolean,
    day: CalendarDay,
    allReminders: State<List<Reminder>>,
    currentSelectedDate: State<LocalDate>,
    onDayClickListener: (CalendarDay) -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(CircleShape)
            .background(
                if (currentSelectedDate.value == day.date && day.position == DayPosition.MonthDate) {
                    if (isDarkTheme) Blue else Orange
                } else {
                    Color.Transparent
                }
            )
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = if (
                    LocalDate.now() == day.date
                    && currentSelectedDate.value != day.date
                    && day.position == DayPosition.MonthDate
                )
                    MaterialTheme.colorScheme.onBackground
                else Color.Transparent
            )
            .clickable {
                if (currentSelectedDate.value != day.date && day.position == DayPosition.MonthDate) {
                    onDayClickListener(day)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (day.position == DayPosition.MonthDate) {
                Text(
                    text = day.date.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    color = if (currentSelectedDate.value != day.date) {
                        MaterialTheme.colorScheme.onBackground
                    } else {
                        White
                    }
                )
                val contains = allReminders.value.find {
                    it.time.toStartOfDay().time == day.date.toDate().toStartOfDay().time
                }
                contains?.let {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(
                                if (currentSelectedDate.value != day.date) {
                                    if (isDarkTheme) Blue else Orange
                                } else {
                                    White
                                }
                            )
                    )
                }
            } else {
                Text(
                    text = day.date.dayOfMonth.toString(),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
                )
            }
        }
    }
}

private val daysOfWeek = listOf(
    DayOfWeek.MONDAY,
    DayOfWeek.TUESDAY,
    DayOfWeek.WEDNESDAY,
    DayOfWeek.THURSDAY,
    DayOfWeek.FRIDAY,
    DayOfWeek.SATURDAY,
    DayOfWeek.SUNDAY
)