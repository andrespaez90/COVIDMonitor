package com.health.monitor.di.components

import com.health.monitor.di.InjectableApplication
import com.health.monitor.di.modules.AppModule
import com.health.monitor.di.modules.NetworkModule
import com.health.monitor.di.modules.ServicesModules
import com.health.monitor.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        ServicesModules::class,
        ViewModelModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface MainComponent : AppComponent, AndroidInjector<InjectableApplication> {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun application(application: InjectableApplication): Builder
    }
}
