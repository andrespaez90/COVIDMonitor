package com.health.monitor

import com.health.monitor.di.InjectableApplication
import com.health.monitor.di.components.DaggerMainComponent
import com.health.monitor.di.components.MainComponent

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