package com.paez.monitor.di.modules

import android.content.Context
import com.paez.monitor.provider.ResourceProvider
import com.paez.monitor.di.InjectableApplication
import com.paez.monitor.managers.preferences.PrefsManager
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