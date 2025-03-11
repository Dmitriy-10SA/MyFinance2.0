package com.andef.myfinance.presentation.viewmodel.income

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.myfinance.domain.database.income.entities.Income
import com.andef.myfinance.domain.database.income.entities.IncomeCategory
import com.andef.myfinance.domain.database.income.usecases.AddIncomeUseCase
import com.andef.myfinance.domain.database.income.usecases.ChangeIncomeUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class IncomeViewModel @Inject constructor(
    private val addIncomeUseCase: AddIncomeUseCase,
    private val changeIncomeUseCase: ChangeIncomeUseCase
) : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    fun addIncome(income: Income) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            addIncomeUseCase.execute(income)
        }
    }

    fun changeIncome(
        income: Income,
        newAmount: Double? = null,
        newCategory: IncomeCategory? = null,
        newComment: String? = null,
        newDate: Date? = null
    ) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            changeIncomeUseCase.execute(
                income,
                newAmount,
                newCategory,
                newComment,
                newDate
            )
        }
    }
}