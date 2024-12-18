package com.test.shopping_lists

import android.app.Application
import com.test.shopping_lists.di.initKoin
import org.koin.android.ext.koin.androidContext

class AndroidApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@AndroidApplication.applicationContext)
        }
    }
}