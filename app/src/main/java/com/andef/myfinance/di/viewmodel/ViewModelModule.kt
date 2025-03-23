package com.andef.myfinance.di.viewmodel

import androidx.lifecycle.ViewModel
import com.andef.myfinance.presentation.analysis.income.IncomeAnalysisViewModel
import com.andef.myfinance.presentation.currency.CurrencyViewModel
import com.andef.myfinance.presentation.expense.ExpenseViewModel
import com.andef.myfinance.presentation.expense.ExpensesScreenViewModel
import com.andef.myfinance.presentation.income.IncomeViewModel
import com.andef.myfinance.presentation.income.IncomesScreenViewModel
import com.andef.myfinance.presentation.main.MainActivityViewModel
import com.andef.myfinance.presentation.total.TotalsScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindMainViewModel(impl: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomesScreenViewModel::class)
    fun bindIncomesScreenViewModel(impl: IncomesScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesScreenViewModel::class)
    fun bindExpensesScreenViewModel(impl: ExpensesScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TotalsScreenViewModel::class)
    fun bindTotalsScreenViewModel(impl: TotalsScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    fun bindIncomeViewModel(impl: IncomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpenseViewModel::class)
    fun bindExpenseViewModel(impl: ExpenseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    fun bindCurrencyViewModel(impl: CurrencyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeAnalysisViewModel::class)
    fun bindIncomeAnalysisViewModel(impl: IncomeAnalysisViewModel): ViewModel
}