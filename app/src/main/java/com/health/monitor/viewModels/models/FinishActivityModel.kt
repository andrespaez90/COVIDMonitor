package com.health.monitor.viewModels.models

import android.content.Intent

class FinishActivityModel(val code: Int) {

    var intent: Intent? = null

    constructor(code: Int, intent: Intent) : this(code) {
        this.intent = intent
    }
}