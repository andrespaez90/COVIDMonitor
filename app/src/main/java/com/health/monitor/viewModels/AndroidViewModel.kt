package com.health.monitor.viewModels

import android.app.Activity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.health.monitor.SelfApplication.Companion.getAppInstance
import com.health.monitor.lifecycle.ActiveMutableLiveData
import com.health.monitor.ui.factories.SnackBarFactory
import com.health.monitor.utils.ErrorUtil
import com.health.monitor.viewModels.models.*
import io.reactivex.disposables.CompositeDisposable

open class AndroidViewModel : ViewModel() {

    protected val disposables = CompositeDisposable()

    protected val loader = MutableLiveData<Boolean>()

    protected val closeView = MutableLiveData<FinishActivityModel>()

    protected val snackBar = ActiveMutableLiveData<SnackBarEvent>()

    protected val alertDialog = MutableLiveData<DialogParams>()

    protected val startActivity = ActiveMutableLiveData<StartActivityModel>()

    protected val startAction = ActiveMutableLiveData<StartActionModel>()

    internal fun showSnackBarError(message: String) {
        showSnackBarMessage(SnackBarFactory.TYPE_ERROR, message, Snackbar.LENGTH_LONG)
    }

    internal fun showSnackBarError(message: Int) {
        showSnackBarMessage(SnackBarFactory.TYPE_ERROR, getAppInstance().getString(message), Snackbar.LENGTH_LONG)
    }

    internal fun showSnackBarMessage(
        @SnackBarFactory.SnackBarType typeSnackBar: String, message: String,
        duration: Int,
        eventClose: (() -> Unit)? = null
    ) {
        snackBar.postValue(SnackBarEvent(typeSnackBar, message, duration, eventClose))
    }

    internal fun showSnackBarMessage(@SnackBarFactory.SnackBarType typeSnackBar: String, message: Int, duration: Int) {
        snackBar.postValue(SnackBarEvent(typeSnackBar, getAppInstance().getString(message), duration))
    }

    open fun showServiceError(throwable: Throwable) {
        showSnackBarMessage(SnackBarFactory.TYPE_ERROR, ErrorUtil.getMessageError(throwable), Snackbar.LENGTH_LONG)
        loader.postValue(false)
    }

    open fun showServiceError(throwable: Throwable, eventClose: (() -> Unit)? = null) {
        showSnackBarMessage(
            SnackBarFactory.TYPE_ERROR,
            ErrorUtil.getMessageError(throwable),
            Snackbar.LENGTH_LONG,
            eventClose
        )
        loader.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
    }

    open fun hideLoading() {
        loader.postValue(false)
    }

    open fun showLoading() {
        loader.postValue(true)
    }

    fun onCloseView() {
        closeView.postValue(FinishActivityModel(Activity.RESULT_OK))
    }

    open fun onSaveInstanceState(): Bundle {
        return Bundle()
    }

    open fun onRestoreInstanceState(savedInstanceState: Bundle) {}

    /**
     * LiveData
     */

    fun loaderState(): LiveData<Boolean> = loader

    fun snackBarMessage(): LiveData<SnackBarEvent> = snackBar

    fun showAlertDialog(): LiveData<DialogParams> = alertDialog

    fun closeView(): LiveData<FinishActivityModel> = closeView

    fun startActivity(): LiveData<StartActivityModel> = startActivity

    fun startAction(): LiveData<StartActionModel> = startAction
}