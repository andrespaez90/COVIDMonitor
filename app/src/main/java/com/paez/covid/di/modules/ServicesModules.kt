package com.barnes.infopumps.di.modules

import com.paez.covid.network.services.MonitorServices
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