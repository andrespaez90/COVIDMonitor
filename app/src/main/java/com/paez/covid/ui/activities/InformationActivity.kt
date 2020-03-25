package com.paez.covid.ui.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.paez.covid.R
import com.paez.covid.databinding.ActivityInformationBinding

class InformationActivity : BaseActivity() {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_information)
        setupActionBar(binding.toolbar)
    }
}
