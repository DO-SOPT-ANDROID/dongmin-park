package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.presentation.ViewModelFactory
import org.sopt.dosopttemplate.utilprivate.makeToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        observeSignupSuccess()
        observeInformation()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel
    }

    private fun observeSignupSuccess() {
        signUpViewModel.signUpSuccess.observe(this) {
            if (it) {
                makeToast(this@SignUpActivity, getString(R.string.SIGNUP_SUCCESS))
                signupSuccessed(signUpViewModel.id.value ?: "", signUpViewModel.pw.value ?: "")
            } else {
                makeToast(this@SignUpActivity, getString(R.string.SIGNUP_ERROR))
            }
        }
    }

    private fun signupSuccessed(id: String, pw: String) {
        with(intent) {
            putExtra("ID", id)
            putExtra("PW", pw)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun observeInformation() {
        observeIdCorrect()
        observePwCorrect()
        observeNicknameCorrect()
    }

    private fun observeIdCorrect() {
        signUpViewModel.isIdValid.observe(this) {
            if (it || signUpViewModel.id.value.isNullOrBlank()) {
                binding.etvSignupId.isErrorEnabled = false
            } else {
                binding.etvSignupId.isErrorEnabled = true
                binding.etvSignupId.error = getString(R.string.ID_ERROR)
            }
        }
    }

    private fun observePwCorrect() {
        signUpViewModel.isPwValid.observe(this) {
            if (it || signUpViewModel.pw.value.isNullOrBlank()) {
                binding.etvSignupPw.isErrorEnabled = false
            } else {
                binding.etvSignupPw.isErrorEnabled = true
                binding.etvSignupPw.error = getString(R.string.PW_ERROR)
            }
        }
    }

    private fun observeNicknameCorrect() {
        signUpViewModel.isNicknameValid.observe(this) {
            if (it || signUpViewModel.nickname.value.isNullOrBlank()) {
                binding.etvSignupNickname.isErrorEnabled = false
            } else {
                binding.etvSignupNickname.isErrorEnabled = true
                binding.etvSignupNickname.error = getString(R.string.NICKNAME_ERROR)
            }
        }
    }
}
