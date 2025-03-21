package com.andef.myfinance.presentation.viewmodel.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.usecases.GetExpensesAmountUseCase
import com.andef.myfinance.domain.expense.usecases.GetExpensesUseCase
import com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase
import com.andef.myfinance.presentation.ui.expense.DetailExpenseBarChartState
import com.andef.myfinance.presentation.ui.expense.DetailExpensePieChartState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class DetailExpenseViewModel @Inject constructor(
    private val getExpensesUseCase: com.andef.myfinance.domain.expense.usecases.GetExpensesUseCase,
    private val getFullAmountExpenseUseCase: com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase,
    private val getExpensesAmountUseCase: com.andef.myfinance.domain.expense.usecases.GetExpensesAmountUseCase
) : ViewModel() {
    private val _detailExpenseBarChartState = MutableLiveData<DetailExpenseBarChartState>()
    val detailExpenseBarChartState: LiveData<DetailExpenseBarChartState>
        get() = _detailExpenseBarChartState

    private val barChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailExpenseBarChartState.value = DetailExpenseBarChartState.Error
    }

    private val _detailExpensePieChartState = MutableLiveData<DetailExpensePieChartState>()
    val detailExpensePieChartState: LiveData<DetailExpensePieChartState>
        get() = _detailExpensePieChartState

    private val pieChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailExpensePieChartState.value = DetailExpensePieChartState.Error
    }

    fun getExpenses(startDate: Date, endDate: Date) = getExpensesUseCase.execute(startDate, endDate)

    fun getFullAmountExpense(
        startDate: Date,
        endDate: Date
    ) = getFullAmountExpenseUseCase.execute(startDate, endDate)

    fun getExpenseAmountForBarChart(expenses: List<com.andef.myfinance.domain.expense.entities.Expense>) {
        viewModelScope.launch(barChartExceptionHandler) {
            _detailExpenseBarChartState.value = DetailExpenseBarChartState.Loading
            val expensesAmount = withContext(Dispatchers.IO) {
                getExpensesAmountUseCase.execute(expenses)
            }
            _detailExpenseBarChartState.value = DetailExpenseBarChartState.ExpensesAmount(expensesAmount)
        }
    }

    fun getExpensesAmountForPieChart(expenses: List<com.andef.myfinance.domain.expense.entities.Expense>) {
        viewModelScope.launch(pieChartExceptionHandler) {
            _detailExpensePieChartState.value = DetailExpensePieChartState.Loading
            val expensesAmountPercent = withContext(Dispatchers.IO) {
                val expensesAmount = getExpensesAmountUseCase.execute(expenses)
                val sum = expensesAmount.sum()
                val productsPercent = (expensesAmount[0] / sum).toFloat()
                val cafePercent = (expensesAmount[1] / sum).toFloat()
                val homePercent = (expensesAmount[2] / sum).toFloat()
                val giftsPercent = (expensesAmount[3] / sum).toFloat()
                val studyPercent = (expensesAmount[4] / sum).toFloat()
                val healthPercent = (expensesAmount[5] / sum).toFloat()
                val transportPercent = (expensesAmount[6] / sum).toFloat()
                val sportPercent = (expensesAmount[7] / sum).toFloat()
                val clothesPercent = (expensesAmount[8] / sum).toFloat()
                val otherPercent = (expensesAmount[9] / sum).toFloat()
                listOf(
                    productsPercent,
                    cafePercent,
                    homePercent,
                    giftsPercent,
                    studyPercent,
                    healthPercent,
                    transportPercent,
                    sportPercent,
                    clothesPercent,
                    otherPercent
                )
            }
            _detailExpensePieChartState.value = DetailExpensePieChartState.ExpensesAmount(expensesAmountPercent)
        }
    }
}