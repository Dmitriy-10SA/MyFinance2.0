package com.andef.myfinance.old_presentation.viewmodel.expense
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.andef.myfinance.domain.expense.entities.Expense
//import com.andef.myfinance.domain.expense.entities.ExpenseCategory
//import com.andef.myfinance.domain.expense.usecases.AddExpenseUseCase
//import com.andef.myfinance.domain.expense.usecases.ChangeExpenseUseCase
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import java.util.Date
//import javax.inject.Inject
//
//class ExpenseViewModel @Inject constructor(
//    private val addExpenseUseCase: com.andef.myfinance.domain.expense.usecases.AddExpenseUseCase,
//    private val changeExpenseUseCase: com.andef.myfinance.domain.expense.usecases.ChangeExpenseUseCase
//) : ViewModel() {
//    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
//
//    fun addExpense(expense: com.andef.myfinance.domain.expense.entities.Expense) {
//        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
//            addExpenseUseCase.execute(expense)
//        }
//    }
//
//    fun changeExpense(
//        expense: com.andef.myfinance.domain.expense.entities.Expense,
//        newAmount: Double? = null,
//        newCategory: com.andef.myfinance.domain.expense.entities.ExpenseCategory? = null,
//        newComment: String? = null,
//        newDate: Date? = null
//    ) {
//        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
//            changeExpenseUseCase.execute(
//                expense,
//                newAmount,
//                newCategory,
//                newComment,
//                newDate
//            )
//        }
//    }
//}