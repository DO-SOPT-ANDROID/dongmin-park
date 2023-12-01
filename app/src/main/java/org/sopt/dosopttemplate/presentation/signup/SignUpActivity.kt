package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.presentation.login.LoginViewModel
import org.sopt.dosopttemplate.server.ServicePool.authService
import org.sopt.dosopttemplate.utilprivate.makeToast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel

        observeSignupSuccess()
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
