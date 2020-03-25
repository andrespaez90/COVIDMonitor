package com.health.monitor.network.models

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

data class CountryHistoryResponse(
        @SerializedName("country") val name: String,
        @SerializedName("stat_by_country") val state: List<CountryHistoryInformation>
)

data class CountryHistoryInformation(
        @SerializedName("country_name") val name: String,
        @SerializedName("total_cases") val _totalCases: String,
        @SerializedName("new_cases") val _newCases: String,
        @SerializedName("active_cases") val _activeCases: String,
        @SerializedName("total_recovered") val _totalRecovered: String,
        @SerializedName("total_deaths") val _totalDeaths: String,
        @SerializedName("record_date") val date: String
) {
    val totalCases: String
        get() = _totalCases.replace(",", "")

    val newCases: String
        get() = _newCases.replace(",", "")

    val activeCases: String
        get() = _activeCases.replace(",", "")

    val totalRecovered: String
        get() = _totalRecovered.replace(",", "")

    val totalDeaths: String
        get() = _totalDeaths.replace(",", "")
}