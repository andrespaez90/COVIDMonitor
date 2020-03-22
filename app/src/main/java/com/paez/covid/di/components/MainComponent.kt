package com.barnes.infopumps.di.components

import com.barnes.infopumps.di.modules.AppModule
import com.barnes.infopumps.di.modules.NetworkModule
import com.barnes.infopumps.di.modules.ServicesModules
import com.barnes.infopumps.di.modules.ViewModelModule
import com.paez.covid.di.InjectableApplication
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
