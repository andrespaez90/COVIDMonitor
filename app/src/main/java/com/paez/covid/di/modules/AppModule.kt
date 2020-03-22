package com.barnes.infopumps.di.modules

import android.content.Context
import com.barnes.infopumps.provider.ResourceProvider
import com.paez.covid.di.InjectableApplication
import com.paez.covid.managers.preferences.PrefsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun appContext(application: InjectableApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun preferenceManager(context: Context): PrefsManager {
        PrefsManager.init(context)
        return PrefsManager.getInstance()
    }

    @Provides
    @Singleton
    fun resourceProvider(context: Context): ResourceProvider {
        return ResourceProvider(context)
    }
}