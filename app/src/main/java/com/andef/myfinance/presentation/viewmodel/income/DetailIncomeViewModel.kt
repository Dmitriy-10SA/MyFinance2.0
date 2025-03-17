package com.andef.myfinance.presentation.viewmodel.income

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.usecases.GetFullAmountIncomeUseCase
import com.andef.myfinance.domain.database.income.usecases.GetIncomesAmountUseCase
import com.andef.myfinance.domain.database.income.usecases.GetIncomesUseCase
import com.andef.myfinance.presentation.ui.income.DetailIncomeBarChartState
import com.andef.myfinance.presentation.ui.income.DetailIncomePieChartState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class DetailIncomeViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getFullAmountIncomeUseCase: GetFullAmountIncomeUseCase,
    private val getIncomesAmount: GetIncomesAmountUseCase
) : ViewModel() {
    private val _detailIncomeBarChartState = MutableLiveData<DetailIncomeBarChartState>()
    val detailIncomeBarChartState: LiveData<DetailIncomeBarChartState>
        get() = _detailIncomeBarChartState

    private val barChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailIncomeBarChartState.value = DetailIncomeBarChartState.Error
    }

    private val _detailIncomePieChartState = MutableLiveData<DetailIncomePieChartState>()
    val detailIncomePieChartState: LiveData<DetailIncomePieChartState>
        get() = _detailIncomePieChartState

    private val pieChartExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _detailIncomePieChartState.value = DetailIncomePieChartState.Error
    }

    fun getIncomes(startDate: Date, endDate: Date) = getIncomesUseCase.execute(startDate, endDate)

    fun getFullAmountIncome(
        startDate: Date,
        endDate: Date
    ) = getFullAmountIncomeUseCase.execute(startDate, endDate)

    fun getIncomesAmountForBarChart(incomes: List<Income>) {
        viewModelScope.launch(barChartExceptionHandler) {
            _detailIncomeBarChartState.value = DetailIncomeBarChartState.Loading
            val incomesAmount = withContext(Dispatchers.IO) {
                getIncomesAmount.execute(incomes)
            }
            _detailIncomeBarChartState.value = DetailIncomeBarChartState.IncomesAmount(incomesAmount)
        }
    }

    fun getIncomesAmountForPieChart(incomes: List<Income>) {
        viewModelScope.launch(pieChartExceptionHandler) {
            _detailIncomePieChartState.value = DetailIncomePieChartState.Loading
            val incomesAmountPercent = withContext(Dispatchers.IO) {
                val incomesAmount = getIncomesAmount.execute(incomes)
                val sum = incomesAmount.sum()
                val salaryPercent = (incomesAmount[0] / sum).toFloat()
                val bankPercent = (incomesAmount[1] / sum).toFloat()
                val luckPercent = (incomesAmount[2] / sum).toFloat()
                val giftsPercent = (incomesAmount[3] / sum).toFloat()
                val otherPercent = (incomesAmount[4] / sum).toFloat()
                listOf(salaryPercent, bankPercent, luckPercent, giftsPercent, otherPercent)
            }
            _detailIncomePieChartState.value = DetailIncomePieChartState.IncomesAmount(incomesAmountPercent)
        }
    }
}