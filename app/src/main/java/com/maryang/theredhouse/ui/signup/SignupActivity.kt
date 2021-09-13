package com.maryang.theredhouse.ui.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.maryang.theredhouse.R
import com.maryang.theredhouse.databinding.ActivitySignupBinding
import com.maryang.theredhouse.event.EnterEventDefinitions
import com.maryang.theredhouse.event.EventDefinition
import com.maryang.theredhouse.ui.base.BaseViewModelActivity
import com.maryang.theredhouse.ui.list.HouseListActivity
import com.maryang.theredhouse.util.PatternUtil
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxkotlin.plusAssign

@AndroidEntryPoint
class SignupActivity : BaseViewModelActivity() {

    private lateinit var binding: ActivitySignupBinding
    override var enterEvent: EventDefinition? = EnterEventDefinitions.signUp()
    override val viewModel by viewModels<SignupViewModel>()

    companion object {
        const val PASSWORD_MIN_LENGTH = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeSignupButtonState()
        setSignup()
    }

    private fun observeSignupButtonState() {
        binding.email.addTextChangedListener { email ->
            viewModel.emailState.onNext(
                !email.isNullOrBlank() && PatternUtil.isEmail(email.toString())
            )
        }
        binding.password.addTextChangedListener { password ->
            viewModel.passwordState.onNext(
                !password.isNullOrBlank() && password.length >= PASSWORD_MIN_LENGTH
            )
        }
        binding.checkPolicy.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.checkPolicyState.onNext(isChecked)
        }
        compositeDisposable += viewModel.buttonState.subscribe {
            binding.signupButton.isEnabled = it
            binding.signupButton.setBackgroundColor(
                if (it) getColor(R.color.red_900)
                else getColor(R.color.gray500)
            )
        }
    }

    private fun setSignup() {
        binding.signupButton.setOnClickListener {
            viewModel.signup(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.checkPolicy.isChecked
            )
        }
        viewModel.signupLiveData.observe(this) { signup ->
            if (signup) {
                startActivity(Intent(this, HouseListActivity::class.java))
                finish()
            }
        }
    }
}
