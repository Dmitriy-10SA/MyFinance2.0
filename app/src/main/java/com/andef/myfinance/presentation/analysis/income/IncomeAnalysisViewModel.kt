package com.andef.myfinance.presentation.analysis.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.usecases.GetAmountForEachTypeIncomeUseCase
import com.andef.myfinance.domain.income.usecases.GetFullIncomeAmountUseCase
import com.andef.myfinance.domain.income.usecases.GetIncomeListUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class IncomeAnalysisViewModel @Inject constructor(
    private val getIncomeListUseCase: GetIncomeListUseCase,
    private val getFullIncomeAmountUseCase: GetFullIncomeAmountUseCase,
    private val getAmountForEachTypeIncomeAmount: GetAmountForEachTypeIncomeUseCase
) : ViewModel() {
    private val _detailIncomeBarChartState = MutableStateFlow(
        IncomeAnalysisBarChartState.Initial as IncomeAnalysisBarChartState
    )
    val detailIncomeBarChartState: StateFlow<IncomeAnalysisBarChartState> =
        _detailIncomeBarChartState

    private val barChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailIncomeBarChartState.value = IncomeAnalysisBarChartState.Error
    }

    private val _detailIncomePieChartState = MutableStateFlow(
        IncomeAnalysisPieChartState.Initial as IncomeAnalysisPieChartState
    )
    val detailIncomePieChartState: StateFlow<IncomeAnalysisPieChartState> =
        _detailIncomePieChartState

    private val pieChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailIncomePieChartState.value = IncomeAnalysisPieChartState.Error
    }

    fun getIncomes(startDate: Date, endDate: Date) = getIncomeListUseCase.execute(startDate, endDate)

    fun getFullAmountIncome(
        startDate: Date,
        endDate: Date
    ) = getFullIncomeAmountUseCase.execute(startDate, endDate)

    fun getIncomesAmountForBarChart(incomes: List<Income>) {
        viewModelScope.launch(barChartExceptionHandler) {
            _detailIncomeBarChartState.value = IncomeAnalysisBarChartState.Loading
            val incomesAmount = withContext(Dispatchers.IO) {
                getAmountForEachTypeIncomeAmount.execute(incomes)
            }
            _detailIncomeBarChartState.value =
                IncomeAnalysisBarChartState.IncomesAmount(incomesAmount)
        }
    }

    fun getIncomesAmountForPieChart(incomes: List<Income>) {
        viewModelScope.launch(pieChartExceptionHandler) {
            _detailIncomePieChartState.value = IncomeAnalysisPieChartState.Loading
            val incomesAmountPercent = withContext(Dispatchers.IO) {
                val incomesAmount = getAmountForEachTypeIncomeAmount.execute(incomes)
                val sum = incomesAmount.sum()
                val salaryPercent = (incomesAmount[0] / sum).toFloat()
                val bankPercent = (incomesAmount[1] / sum).toFloat()
                val luckPercent = (incomesAmount[2] / sum).toFloat()
                val giftsPercent = (incomesAmount[3] / sum).toFloat()
                val otherPercent = (incomesAmount[4] / sum).toFloat()
                listOf(salaryPercent, bankPercent, luckPercent, giftsPercent, otherPercent)
            }
            _detailIncomePieChartState.value =
                IncomeAnalysisPieChartState.IncomesAmountPercent(incomesAmountPercent)
        }
    }
}