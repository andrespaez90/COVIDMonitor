package com.health.monitor.di.components

import android.content.Context
import com.google.gson.Gson
import com.health.monitor.managers.preferences.PrefsManager
import retrofit2.Retrofit

interface AppComponent {

    fun preferenceManager(): PrefsManager

    fun context(): Context

    fun gson(): Gson

    /**
     * APIs
     */

    fun retrofit(): Retrofit

}