package com.paez.covid.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paez.covid.network.api.ApiMonitor
import com.paez.covid.network.models.CountryHistoryResponse
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(private val monitorApi: ApiMonitor) : AndroidViewModel() {

    private val countryInformation = MutableLiveData<CountryHistoryResponse>()

    fun updateInformation(countryName: String) {
        disposables.add(monitorApi.getHistoryByCountry(countryName)
            .doOnSubscribe { showLoading() }
            .doFinally { hideLoading() }
            .subscribe({ countryInformation.postValue(it) }, ::showServiceError)
        )
    }


    /**
     * LiveData
     */

    fun onDataChange(): LiveData<CountryHistoryResponse> = countryInformation

}