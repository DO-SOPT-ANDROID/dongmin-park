package org.sopt.dosopttemplate.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.utilprivate.makeToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels { SignUpViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        observeSignupSuccess()
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
}
