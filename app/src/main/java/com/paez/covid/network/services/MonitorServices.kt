package com.barnes.infopumps.network.services


import com.paez.covid.network.models.CasesByCountryResponse
import com.paez.covid.network.models.CountriesAffectedResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MonitorServices {

    @GET("affected.php")
    fun getCountriesAffected(): Single<CountriesAffectedResponse>

    @GET("cases_by_country.php")
    fun getCasesByCountry(): Single<CasesByCountryResponse>

    @GET("cases_by_particular_country.php")
    fun getHistoryByCountry(@Query("country") countryName: String): Completable

}