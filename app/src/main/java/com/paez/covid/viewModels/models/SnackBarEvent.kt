package com.barnes.infopumps.viewModels.models

import com.google.android.material.snackbar.Snackbar
import com.paez.covid.ui.factories.SnackBarFactory

data class SnackBarEvent @JvmOverloads constructor(
    @field:SnackBarFactory.SnackBarType
    @get:SnackBarFactory.SnackBarType
    val typeSnackBar: String, val message: String, val duration: Int, val action: (() -> Unit)? = null
)

class OnDismissCallback(private val action: () -> Unit) : Snackbar.Callback() {
    override fun onDismissed(transientBottomBar: Snackbar, event: Int) {
        super.onDismissed(transientBottomBar, event)
        action()
    }
}