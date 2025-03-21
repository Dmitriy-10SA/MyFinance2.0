package com.andef.myfinance.old_presentation.viewmodel.income
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.andef.myfinance.domain.income.entities.Income
//import com.andef.myfinance.domain.income.entities.IncomeCategory
//import com.andef.myfinance.domain.income.usecases.AddIncomeUseCase
//import com.andef.myfinance.domain.income.usecases.ChangeIncomeUseCase
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.Date
//import javax.inject.Inject
//
//class IncomeViewModel @Inject constructor(
//    private val addIncomeUseCase: com.andef.myfinance.domain.income.usecases.AddIncomeUseCase,
//    private val changeIncomeUseCase: com.andef.myfinance.domain.income.usecases.ChangeIncomeUseCase
//) : ViewModel() {
//    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
//
//    fun addIncome(income: com.andef.myfinance.domain.income.entities.Income) {
//        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
//            addIncomeUseCase.execute(income)
//        }
//    }
//
//    fun changeIncome(
//        income: com.andef.myfinance.domain.income.entities.Income,
//        newAmount: Double? = null,
//        newCategory: com.andef.myfinance.domain.income.entities.IncomeCategory? = null,
//        newComment: String? = null,
//        newDate: Date? = null
//    ) {
//        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
//            changeIncomeUseCase.execute(
//                income,
//                newAmount,
//                newCategory,
//                newComment,
//                newDate
//            )
//        }
//    }
//}