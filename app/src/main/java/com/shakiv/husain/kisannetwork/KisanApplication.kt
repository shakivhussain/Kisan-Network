package com.shakiv.husain.kisannetwork

import android.app.Application
import com.shakiv.husain.kisannetwork.di.AppComponent
import com.shakiv.husain.kisannetwork.di.DaggerAppComponent

class KisanApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

}