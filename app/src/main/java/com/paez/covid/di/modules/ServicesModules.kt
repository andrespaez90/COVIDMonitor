package com.barnes.infopumps.di.modules

import com.barnes.infopumps.network.services.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServicesModules {

    @Provides
    @Singleton
    fun catalogServices(retrofit: Retrofit): MonitorServices =
            retrofit.create(MonitorServices::class.java)

}