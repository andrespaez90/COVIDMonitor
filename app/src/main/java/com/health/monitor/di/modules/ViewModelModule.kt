package com.health.monitor.di.modules

import androidx.lifecycle.ViewModel
import com.health.monitor.viewModels.CountryDetailViewModel
import com.health.monitor.viewModels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailViewModel::class)
    abstract fun bindCountryDeatilViewModel(countryDetailViewModel: CountryDetailViewModel): ViewModel

}
