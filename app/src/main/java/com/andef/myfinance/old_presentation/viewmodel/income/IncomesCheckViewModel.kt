package com.andef.myfinance.old_presentation.viewmodel.income
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.andef.myfinance.domain.income.usecases.GetFullAmountIncomeUseCase
//import com.andef.myfinance.domain.income.usecases.GetIncomesUseCase
//import com.andef.myfinance.domain.income.usecases.RemoveIncomeUseCase
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.Date
//import javax.inject.Inject
//
//class IncomesCheckViewModel @Inject constructor(
//    private val getIncomesUseCase: com.andef.myfinance.domain.income.usecases.GetIncomesUseCase,
//    private val getFullAmountUseCase: com.andef.myfinance.domain.income.usecases.GetFullAmountIncomeUseCase,
//    private val removeIncomeUseCase: com.andef.myfinance.domain.income.usecases.RemoveIncomeUseCase
//) : ViewModel() {
//    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
//
//    fun getIncomes(
//        startDate: Date,
//        endDate: Date
//    ) = getIncomesUseCase.execute(startDate, endDate)
//
//    fun getFullAmount(
//        startDate: Date,
//        endDate: Date
//    ) = getFullAmountUseCase.execute(startDate, endDate)
//
//    fun removeIncome(id: Int) {
//        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
//            removeIncomeUseCase.execute(id)
//        }
//    }
//}