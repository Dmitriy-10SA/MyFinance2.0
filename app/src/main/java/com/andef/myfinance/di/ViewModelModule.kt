package com.andef.myfinance.di

import androidx.lifecycle.ViewModel
import com.andef.myfinance.presentation.viewmodel.currency.CurrencyViewModel
import com.andef.myfinance.presentation.viewmodel.expense.DetailExpenseViewModel
import com.andef.myfinance.presentation.viewmodel.expense.ExpenseViewModel
import com.andef.myfinance.presentation.viewmodel.expense.ExpensesCheckViewModel
import com.andef.myfinance.presentation.viewmodel.income.DetailIncomeViewModel
import com.andef.myfinance.presentation.viewmodel.income.IncomeViewModel
import com.andef.myfinance.presentation.viewmodel.income.IncomesCheckViewModel
import com.andef.myfinance.presentation.viewmodel.total.TotalViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(IncomesCheckViewModel::class)
    @Binds
    fun bindIncomesCheckViewModel(impl: IncomesCheckViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ExpensesCheckViewModel::class)
    @Binds
    fun bindExpensesCheckViewModel(impl: ExpensesCheckViewModel): ViewModel

    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    @Binds
    fun bindIncomeViewModel(impl: IncomeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(ExpenseViewModel::class)
    @Binds
    fun bindExpenseViewModel(impl: ExpenseViewModel): ViewModel

    @IntoMap
    @ViewModelKey(TotalViewModel::class)
    @Binds
    fun bindTotalViewModel(impl: TotalViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    @Binds
    fun bindCurrencyViewModel(impl: CurrencyViewModel): ViewModel

    @IntoMap
    @ViewModelKey(DetailIncomeViewModel::class)
    @Binds
    fun bindDetailIncomeViewModel(impl: DetailIncomeViewModel): ViewModel

    @IntoMap
    @ViewModelKey(DetailExpenseViewModel::class)
    @Binds
    fun bindDetailExpenseViewModel(impl: DetailExpenseViewModel): ViewModel
}