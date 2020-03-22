package com.barnes.infopumps.ui.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.paez.covid.SelfApplication.Companion.getAppInstance
import com.paez.covid.ui.activities.BaseActivity
import com.paez.covid.ui.fragments.BaseFragment

inline fun <reified T : ViewModel> BaseActivity.provideViewModel(): T =
    ViewModelProviders.of(this, getAppInstance().viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> BaseFragment.provideViewModel(): T =
    ViewModelProviders.of(this, getAppInstance().viewModelFactory)[T::class.java]

inline fun <reified T : ViewModel> BaseFragment.provideViewModelWithActivityScope(): T =
    ViewModelProviders.of(requireActivity(), getAppInstance().viewModelFactory)[T::class.java]