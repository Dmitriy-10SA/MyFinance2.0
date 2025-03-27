package com.andef.myfinance.di

import android.app.Application
import com.andef.myfinance.presentation.reminder.ReminderActivity
import com.andef.myfinance.di.currency.CurrencyApiServiceModule
import com.andef.myfinance.di.currency.CurrencyRepositoryModule
import com.andef.myfinance.di.expense.ExpenseDaoModule
import com.andef.myfinance.di.expense.ExpenseRepositoryModule
import com.andef.myfinance.di.income.IncomeDaoModule
import com.andef.myfinance.di.income.IncomeRepositoryModule
import com.andef.myfinance.di.purpose.active.ActivePurposeDaoModule
import com.andef.myfinance.di.purpose.active.ActivePurposeRepositoryModule
import com.andef.myfinance.di.purpose.completed.CompletedPurposeDaoModule
import com.andef.myfinance.di.purpose.completed.CompletedPurposeRepositoryModule
import com.andef.myfinance.di.reminder.ReminderDaoModule
import com.andef.myfinance.di.reminder.ReminderRepositoryModule
import com.andef.myfinance.di.theme.ThemePreferencesRepositoryModule
import com.andef.myfinance.di.theme.ThemeSharedPreferencesModule
import com.andef.myfinance.di.viewmodel.ViewModelModule
import com.andef.myfinance.presentation.analysis.expense.ExpenseAnalysisActivity
import com.andef.myfinance.presentation.analysis.income.IncomeAnalysisActivity
import com.andef.myfinance.presentation.currency.CurrencyActivity
import com.andef.myfinance.presentation.expense.ExpenseActivity
import com.andef.myfinance.presentation.income.IncomeActivity
import com.andef.myfinance.presentation.main.MainActivity
import com.andef.myfinance.presentation.reminder.ReminderListActivity
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
        ViewModelModule::class,
        ReminderRepositoryModule::class,
        ReminderDaoModule::class,
        ActivePurposeDaoModule::class,
        ActivePurposeRepositoryModule::class,
        CompletedPurposeDaoModule::class,
        CompletedPurposeRepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(incomeActivity: IncomeActivity)
    fun inject(expenseActivity: ExpenseActivity)
    fun inject(currencyActivity: CurrencyActivity)
    fun inject(incomeAnalysisActivity: IncomeAnalysisActivity)
    fun inject(expenseAnalysisActivity: ExpenseAnalysisActivity)
    fun inject(reminderListActivity: ReminderListActivity)
    fun inject(reminderActivity: ReminderActivity)

    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}