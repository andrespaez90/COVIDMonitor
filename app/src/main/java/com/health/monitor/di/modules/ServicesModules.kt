package com.health.monitor.di.modules

import com.health.monitor.network.services.MonitorServices
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