package org.sopt.dosopttemplate

import android.os.Bundle
import android.util.Log
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.model.requestModel.RequestSignupDto
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
                            makeToast(this@SignUpActivity, "회원가입 성공")
                            signupSuccessed(username, password)
                        }
                    }

                    override fun onFailure(call: Call<Unit>, t: Throwable) {
                        makeToast(this@SignUpActivity, "서버 오류")
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
