package com.paez.covid

import com.barnes.infopumps.di.components.DaggerMainComponent
import com.barnes.infopumps.di.components.MainComponent
import com.paez.covid.di.InjectableApplication

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