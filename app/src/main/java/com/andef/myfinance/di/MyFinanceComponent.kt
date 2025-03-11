package com.andef.myfinance.di

import android.app.Application
import com.andef.myfinance.di.database.expense.ExpenseDaoModule
import com.andef.myfinance.di.database.expense.ExpenseRepositoryModule
import com.andef.myfinance.di.database.income.IncomeDaoModule
import com.andef.myfinance.di.database.income.IncomeRepositoryModule
import com.andef.myfinance.di.network.CurrencyRubApiServiceModule
import com.andef.myfinance.di.network.CurrencyRubRepositoryModule
import com.andef.myfinance.presentation.activity.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        ExpenseRepositoryModule::class,
        IncomeRepositoryModule::class,
        ExpenseDaoModule::class,
        IncomeDaoModule::class,
        ViewModelModule::class,
        CurrencyRubApiServiceModule::class,
        CurrencyRubRepositoryModule::class
    ]
)
interface MyFinanceComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface MyFinanceFactory {
        fun create(@BindsInstance application: Application): MyFinanceComponent
    }
}