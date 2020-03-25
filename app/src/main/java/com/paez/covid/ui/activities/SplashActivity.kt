package com.paez.covid.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.paez.covid.R
import com.paez.covid.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        binding.textViewTitle.postDelayed({ startMainActivity() }, 4000)
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
