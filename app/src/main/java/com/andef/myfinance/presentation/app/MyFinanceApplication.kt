package com.andef.myfinance.presentation.app

import android.app.Application
import com.andef.myfinance.di.DaggerMyFinanceComponent

class MyFinanceApplication : Application() {
    val component by lazy {
        DaggerMyFinanceComponent.factory().create(this)
    }
}