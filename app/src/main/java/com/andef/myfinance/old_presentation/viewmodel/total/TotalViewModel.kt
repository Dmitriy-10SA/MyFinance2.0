package com.andef.myfinance.old_presentation.viewmodel.total
//
//import androidx.lifecycle.ViewModel
//import com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase
//import com.andef.myfinance.domain.income.usecases.GetFullAmountIncomeUseCase
//import java.util.Date
//import javax.inject.Inject
//
//class TotalViewModel @Inject constructor(
//    private val getFullAmountIncomeUseCase: com.andef.myfinance.domain.income.usecases.GetFullAmountIncomeUseCase,
//    private val getFullAmountExpenseUseCase: com.andef.myfinance.domain.expense.usecases.GetFullAmountExpenseUseCase
//) : ViewModel() {
//    fun getFullAmountIncome(
//        startDate: Date,
//        endDate: Date
//    ) = getFullAmountIncomeUseCase.execute(startDate, endDate)
//
//    fun getFullAmountExpense(
//        startDate: Date,
//        endDate: Date
//    ) = getFullAmountExpenseUseCase.execute(startDate, endDate)
//}