package com.health.monitor.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.health.monitor.R
import com.health.monitor.databinding.ActivityInformationBinding

class InformationActivity : BaseActivity() {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)
        setupActionBar(binding.toolbar)
    }
}
