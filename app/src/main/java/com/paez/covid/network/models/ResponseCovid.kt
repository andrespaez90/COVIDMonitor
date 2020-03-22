package com.paez.covid.network.models

import com.google.gson.annotations.SerializedName

data class CountriesAffectedResponse(
        @SerializedName("affected_countries") val countries: List<String>,
        @SerializedName("statistic_taken_at") val dateUpdated: String
)

data class CasesByCountryResponse(
        @SerializedName("countries_stat") val countries: List<CountryCases>,
        @SerializedName("statistic_taken_at") val date: String
)

data class CountryCases(
        @SerializedName("country_name") val name: String,
        @SerializedName("cases") val cases: String,
        @SerializedName("new_cases") val newCases: String,
        @SerializedName("deaths") val deaths: String,
        @SerializedName("total_recovered") val totalRecovered: String,
        @SerializedName("active_cases") val activeCases: String
)