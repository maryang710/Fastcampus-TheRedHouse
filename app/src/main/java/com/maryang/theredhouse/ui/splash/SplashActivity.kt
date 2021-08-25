package com.maryang.theredhouse.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.maryang.theredhouse.databinding.ActivitySplashBinding
import com.maryang.theredhouse.ui.base.BaseViewModelActivity
import com.maryang.theredhouse.ui.list.HouseListActivity
import com.maryang.theredhouse.ui.signup.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseViewModelActivity() {

    private lateinit var binding: ActivitySplashBinding
    override val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.shouldSignupLiveData.observe(this) { shouldSignup ->
            if (shouldSignup) {
                startActivity(Intent(this, SignupActivity::class.java))
            } else {
                startActivity(Intent(this, HouseListActivity::class.java))
            }
            finish()
        }
        viewModel.onCreate()
    }
}
