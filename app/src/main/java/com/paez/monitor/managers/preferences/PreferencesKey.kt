package com.paez.monitor.managers.preferences

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

