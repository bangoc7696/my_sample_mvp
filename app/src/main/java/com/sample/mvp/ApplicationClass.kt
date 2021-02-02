package com.sample.mvp

import android.app.Application
import com.sample.mvp.di.component.ApplicationComponent
import com.sample.mvp.di.component.DaggerApplicationComponent
import com.sample.mvp.di.module.NetModule

open class ApplicationClass : Application() {


    public lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(NetModule())
                .build()

        applicationComponent.inject(this)
    }
}