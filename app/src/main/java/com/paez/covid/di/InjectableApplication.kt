package com.paez.covid.di

import androidx.multidex.MultiDexApplication
import com.barnes.infopumps.di.factories.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class InjectableApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun androidInjector(): AndroidInjector<Any> = activityDispatchingAndroidInjector

    abstract fun initializeInjector()
}