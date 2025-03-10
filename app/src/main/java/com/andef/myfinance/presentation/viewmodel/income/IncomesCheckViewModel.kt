package com.andef.myfinance.presentation.viewmodel.income

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.income.usecases.GetFullAmountUseCase
import com.andef.myfinance.domain.database.income.usecases.GetIncomesUseCase
import com.andef.myfinance.presentation.ui.income.IncomesState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomesCheckViewModel @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getFullAmountUseCase: GetFullAmountUseCase
) : ViewModel() {
    private val _state = MutableLiveData<IncomesState>()
    val state: LiveData<IncomesState>
        get() = _state

    fun setDates(startDate: Date, endDate: Date) {
        incomesSubscribe(startDate, endDate)
        amountSubscribe(startDate, endDate)
    }

    private fun amountSubscribe(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            getFullAmountUseCase.execute(startDate, endDate)
                .onStart {
                    _state.value = IncomesState.Loading
                }
                .catch {
                    _state.value = IncomesState.Error
                }
                .collect {
                    _state.value = IncomesState.Amount(it)
                }
        }
    }

    private fun incomesSubscribe(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            getIncomesUseCase.execute(startDate, endDate)
                .onStart {
                    _state.value = IncomesState.Loading
                }
                .catch {
                    _state.value = IncomesState.Error
                }
                .collect {
                    _state.value = IncomesState.Incomes(it)
                }
        }
    }
}