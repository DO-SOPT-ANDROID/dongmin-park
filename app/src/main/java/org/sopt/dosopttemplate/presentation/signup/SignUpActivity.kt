package org.sopt.dosopttemplate.presentation.signup

import android.os.Bundle
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.server.ServicePool.authService
import org.sopt.dosopttemplate.utilprivate.makeToast
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : BaseActivity<ActivitySignupBinding>({ ActivitySignupBinding.inflate(it) }) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        signupBtn()
    }

    private fun signupBtn() =
        binding.btnSignupNaviLogin.setOnClickListener {
            val username = binding.etvSignupId.text.toString()
            val password = binding.etvSignupPw.text.toString()
            val nickname = binding.etvSignupNickname.text.toString()

            authService.signup(RequestSignupDto(username, password, nickname))
                .enqueue(object : retrofit2.Callback<Unit> {
                    override fun onResponse(
                        call: Call<Unit>,
                        response: Response<Unit>,
                    ) {
                        if (response.isSuccessful) {
                            makeToast(this@SignUpActivity, getString(R.string.SIGNUP_SUCCESS))
                            signupSuccessed(username, password)
                        } else
                            makeToast(this@SignUpActivity, getString(R.string.SIGNUP_ERROR))
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        makeToast(this@SignUpActivity, getString(R.string.SERVER_ERROR))
                    }
                })
        }

    private fun signupSuccessed(username: String, password: String) {
        with(intent) {
            putExtra("ID", username)
            putExtra("PW", password)
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}
