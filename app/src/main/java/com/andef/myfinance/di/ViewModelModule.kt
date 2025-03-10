package com.andef.myfinance.di

import androidx.lifecycle.ViewModel
import com.andef.myfinance.presentation.viewmodel.expense.ExpensesCheckViewModel
import com.andef.myfinance.presentation.viewmodel.income.IncomesCheckViewModel
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
}