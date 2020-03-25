package com.paez.monitor.di.components

import com.paez.monitor.di.InjectableApplication
import com.paez.monitor.di.modules.AppModule
import com.paez.monitor.di.modules.NetworkModule
import com.paez.monitor.di.modules.ServicesModules
import com.paez.monitor.di.modules.ViewModelModule
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
