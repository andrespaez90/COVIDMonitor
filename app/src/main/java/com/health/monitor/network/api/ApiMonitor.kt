package com.health.monitor.network.api

import com.health.monitor.network.models.CasesByCountryResponse
import com.health.monitor.network.models.CountryHistoryResponse
import com.health.monitor.network.services.MonitorServices
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class ApiMonitor @Inject constructor(private val services: MonitorServices) : BaseApi() {

    fun getCasesByCountry(scheduler: Scheduler? = null): Single<CasesByCountryResponse> =
        subscribe(services.getCasesByCountry(), scheduler)

    fun getHistoryByCountry(countryName: String, scheduler: Scheduler? = null): Single<CountryHistoryResponse> =
        subscribe(services.getHistoryByCountry(countryName), scheduler)
}