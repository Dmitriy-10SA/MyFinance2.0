package com.andef.myfinance.di

import android.app.Application
import com.andef.myfinance.MainActivity
import com.andef.myfinance.di.currency.CurrencyApiServiceModule
import com.andef.myfinance.di.currency.CurrencyRepositoryModule
import com.andef.myfinance.di.expense.ExpenseDaoModule
import com.andef.myfinance.di.expense.ExpenseRepositoryModule
import com.andef.myfinance.di.income.IncomeDaoModule
import com.andef.myfinance.di.income.IncomeRepositoryModule
import com.andef.myfinance.di.theme.ThemePreferencesRepositoryModule
import com.andef.myfinance.di.theme.ThemeSharedPreferencesModule
import com.andef.myfinance.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        CurrencyApiServiceModule::class,
        CurrencyRepositoryModule::class,
        ExpenseDaoModule::class,
        ExpenseRepositoryModule::class,
        IncomeDaoModule::class,
        IncomeRepositoryModule::class,
        ThemePreferencesRepositoryModule::class,
        ThemeSharedPreferencesModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}