package com.andef.myfinance.di.viewmodel

import androidx.lifecycle.ViewModel
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
}