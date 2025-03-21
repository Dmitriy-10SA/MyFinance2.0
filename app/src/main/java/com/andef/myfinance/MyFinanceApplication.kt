package com.andef.myfinance

import android.app.Application
import com.andef.myfinance.di.DaggerAppComponent

class MyFinanceApplication : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}