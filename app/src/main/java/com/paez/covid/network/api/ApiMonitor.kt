package com.paez.covid.network.api

import com.barnes.infopumps.network.services.MonitorServices
import com.paez.covid.network.models.CasesByCountryResponse
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class ApiMonitor @Inject constructor(private val services: MonitorServices) : BaseApi() {

    fun getCasesByCountry(scheduler: Scheduler? = null): Single<CasesByCountryResponse> =
            subscribe(services.getCasesByCountry(), scheduler)
}