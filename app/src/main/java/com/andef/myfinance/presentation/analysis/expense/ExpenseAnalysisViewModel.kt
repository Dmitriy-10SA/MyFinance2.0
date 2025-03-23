package com.andef.myfinance.presentation.analysis.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.expense.entities.Expense
import com.andef.myfinance.domain.expense.usecases.GetAmountForEachTypeExpenseUseCase
import com.andef.myfinance.domain.expense.usecases.GetExpenseListUseCase
import com.andef.myfinance.domain.expense.usecases.GetFullExpenseAmountUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class ExpenseAnalysisViewModel @Inject constructor(
    private val getExpenseListUseCase: GetExpenseListUseCase,
    private val getFullExpenseAmountUseCase: GetFullExpenseAmountUseCase,
    private val getAmountForEachTypeExpenseAmount: GetAmountForEachTypeExpenseUseCase
) : ViewModel() {
    private val _detailExpenseBarChartState = MutableStateFlow(
        ExpenseAnalysisBarChartState.Initial as ExpenseAnalysisBarChartState
    )
    val detailExpenseBarChartState: StateFlow<ExpenseAnalysisBarChartState> =
        _detailExpenseBarChartState

    private val barChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailExpenseBarChartState.value = ExpenseAnalysisBarChartState.Error
    }

    private val _detailExpensePieChartState = MutableStateFlow(
        ExpenseAnalysisPieChartState.Initial as ExpenseAnalysisPieChartState
    )
    val detailExpensePieChartState: StateFlow<ExpenseAnalysisPieChartState> =
        _detailExpensePieChartState

    private val pieChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailExpensePieChartState.value = ExpenseAnalysisPieChartState.Error
    }

    fun getExpenses(startDate: Date, endDate: Date) =
        getExpenseListUseCase.execute(startDate, endDate)

    fun getFullAmountExpense(
        startDate: Date,
        endDate: Date
    ) = getFullExpenseAmountUseCase.execute(startDate, endDate)

    fun getExpenseAmountForBarChart(expenses: List<Expense>) {
        viewModelScope.launch(barChartExceptionHandler) {
            _detailExpenseBarChartState.value = ExpenseAnalysisBarChartState.Loading
            val expensesAmount = withContext(Dispatchers.IO) {
                getAmountForEachTypeExpenseAmount.execute(expenses)
            }
            _detailExpenseBarChartState.value =
                ExpenseAnalysisBarChartState.ExpenseAmount(expensesAmount)
        }
    }

    fun getExpensesAmountForPieChart(expenses: List<Expense>) {
        viewModelScope.launch(pieChartExceptionHandler) {
            _detailExpensePieChartState.value = ExpenseAnalysisPieChartState.Loading
            val expensesAmountPercent = withContext(Dispatchers.IO) {
                val expensesAmount = getAmountForEachTypeExpenseAmount.execute(expenses)
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
            _detailExpensePieChartState.value =
                ExpenseAnalysisPieChartState.ExpenseAmountPercent(expensesAmountPercent)
        }
    }
}