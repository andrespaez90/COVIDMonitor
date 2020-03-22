package com.paez.covid.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paez.covid.network.api.ApiMonitor
import com.paez.covid.network.models.CasesByCountryResponse
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val monitoApi: ApiMonitor) : AndroidViewModel() {

    private val countryInformation = MutableLiveData<CasesByCountryResponse>()

    init {
        updateInformation()
    }

    fun updateInformation() {
        disposables.add(monitoApi.getCasesByCountry()
                .doOnSubscribe { showLoading() }
                .doFinally { hideLoading() }
                .subscribe({ countryInformation.postValue(it) }, ::showServiceError))
    }

    /**
     * LiveData
     */

    fun onDataChange(): LiveData<CasesByCountryResponse> = countryInformation

}