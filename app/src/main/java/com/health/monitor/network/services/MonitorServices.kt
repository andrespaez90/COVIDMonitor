package com.health.monitor.network.services

import com.health.monitor.network.models.CasesByCountryResponse
import com.health.monitor.network.models.CountriesAffectedResponse
import com.health.monitor.network.models.CountryHistoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MonitorServices {

    @GET("affected.php")
    fun getCountriesAffected(): Single<CountriesAffectedResponse>

    @GET("cases_by_country.php")
    fun getCasesByCountry(): Single<CasesByCountryResponse>

    @GET("cases_by_particular_country.php")
    fun getHistoryByCountry(@Query("country") countryName: String): Single<CountryHistoryResponse>

}