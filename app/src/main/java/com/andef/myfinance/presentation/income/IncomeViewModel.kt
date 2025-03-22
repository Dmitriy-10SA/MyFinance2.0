package com.andef.myfinance.presentation.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.income.entities.Income
import com.andef.myfinance.domain.income.entities.IncomeCategory
import com.andef.myfinance.domain.income.usecases.AddIncomeUseCase
import com.andef.myfinance.domain.income.usecases.ChangeIncomeUseCase
import com.andef.myfinance.domain.income.usecases.GetIncomeUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

class IncomeViewModel @Inject constructor(
    private val addIncomeUseCase: AddIncomeUseCase,
    private val changeIncomeUseCase: ChangeIncomeUseCase,
    private val getIncomeUseCase: GetIncomeUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.value = IncomeState.Error
    }

    private val _state = MutableStateFlow(IncomeState.Initial as IncomeState)
    val state: StateFlow<IncomeState> = _state

    fun addIncome(income: Income) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addIncomeUseCase.execute(income)
        }
    }

    fun changeIncome(
        id: Int,
        amount: Double,
        category: IncomeCategory,
        comment: String,
        date: Date
    ) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeIncomeUseCase.execute(id, amount, category, comment, date)
        }
    }

    fun loadIncome(id: Int?) {
        viewModelScope.launch(exceptionHandler) {
            _state.value = IncomeState.Loading
            if (id != null) {
                val income = withContext(Dispatchers.IO) {
                    getIncomeUseCase.execute(id)
                }
                _state.value = IncomeState.IncomeLoad(income)
            } else {
                _state.value = IncomeState.IncomeLoad(null)
            }
        }
    }
}