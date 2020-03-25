package com.paez.monitor.di.modules

import com.paez.monitor.network.services.MonitorServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServicesModules {

    @Provides
    @Singleton
    fun catalogServices(retrofit: Retrofit): MonitorServices =
            retrofit.create(MonitorServices::class.java)

}