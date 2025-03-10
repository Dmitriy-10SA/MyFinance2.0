package com.andef.myfinance.presentation.viewmodel.income

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
    private val _incomesState = MutableLiveData<IncomesState>()
    val incomes: LiveData<IncomesState>
        get() = _incomesState

    fun setDates(startDate: Date, endDate: Date) {
        viewModelScope.launch {
            getIncomesUseCase.execute(startDate, endDate)
                .onStart {
                    _incomesState.value = IncomesState.Loading as IncomesState
                }
                .catch {
                    _incomesState.value = IncomesState.Error
                }
                .collect {
                    _incomesState.value = IncomesState.Incomes(it)
                }
        }
    }
}