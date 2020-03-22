package com.paez.covid.managers.preferences

import com.barnes.infopumps.managers.preferences.FilePreference

open class PreferencesKey(val preferenceGroupName: String, val key: String, val defaultValue: Any) {

    constructor(key: String, value: Any) : this(FilePreference.DefaultPreference, key, value)

    init {
        ManagerPreferenceKey.addPreferenceKey(this)
    }

    override fun toString(): String {
        return key
    }
}

class SettingsPreferencesKey(key: String, value: Any) : PreferencesKey("", key, value)

