package com.paez.monitor.viewModels

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.paez.monitor.viewModels.models.StartActivityModel
import com.paez.monitor.network.api.ApiMonitor
import com.paez.monitor.network.models.CasesByCountryResponse
import com.paez.monitor.ui.activities.COUNTRY_NAME_DETAIL_ACTIVITY
import com.paez.monitor.ui.activities.CountyDetailActivity
import com.paez.monitor.ui.factories.ItemListener
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val monitorApi: ApiMonitor) : AndroidViewModel(), ItemListener {

    private val countryInformation = MutableLiveData<CasesByCountryResponse>()

    init {
        updateInformation()
    }

    fun updateInformation() {
        disposables.add(monitorApi.getCasesByCountry()
                .doOnSubscribe { showLoading() }
                .doFinally { hideLoading() }
                .subscribe({ countryInformation.postValue(it) }, ::showServiceError))
    }

    /**
     * Listerner
     */

    override fun onCountrySelect(countryName: String) {
        startActivity.postValue(StartActivityModel(CountyDetailActivity::class.java, Bundle()
                .apply { putString(COUNTRY_NAME_DETAIL_ACTIVITY, countryName) }))
    }

    /**
     * LiveData
     */

    fun onDataChange(): LiveData<CasesByCountryResponse> = countryInformation

}