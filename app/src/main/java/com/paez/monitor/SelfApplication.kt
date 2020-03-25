package com.paez.monitor

import com.paez.monitor.di.InjectableApplication
import com.paez.monitor.di.components.DaggerMainComponent
import com.paez.monitor.di.components.MainComponent

class SelfApplication : InjectableApplication() {

    lateinit var appComponent: MainComponent

    companion object {

        protected lateinit var sInstance: SelfApplication

        fun getAppInstance(): SelfApplication =
            sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        initializeInjector()
    }

    override fun initializeInjector() {
       appComponent = DaggerMainComponent
            .builder()
            .application(this).build()
        appComponent.inject(this)
    }
}