package org.sopt.dosopttemplate.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SignUpActivity
import org.sopt.dosopttemplate.server.ServicePool.authService
import org.sopt.dosopttemplate.utilprivate.makeToast
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getIntentInfo()
        loginBtn()
        signUpBtn()
    }

    private fun getIntentInfo() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    setIDPW(result)
                }
            }
    }

    private fun setIDPW(result: ActivityResult) {
        val id = result.data?.getStringExtra("ID")
        val pw = result.data?.getStringExtra("PW")

        binding.etvLoginId.setText(id)
        binding.etvLoginPw.setText(pw)
    }

    private fun loginBtn() {
        binding.btnLoginNaviLogIn.setOnClickListener {
            val id = binding.etvLoginId.text.toString()
            val password = binding.etvLoginPw.text.toString()

            authService.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        when (response.isSuccessful) {
                            true -> {
                                val data: ResponseLoginDto = response.body()!!
                                val userId = data.id
                                val username = data.username
                                val nickname = data.nickname
                                val user =
                                    User(id = userId, username = username, nickname = nickname)

                                moveHomeActivity(user)
                            }

                            false -> {
                                makeToast(this@LoginActivity, getString(R.string.USER_INFO_ERROR))
                            }
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        makeToast(this@LoginActivity, getString(R.string.SERVER_ERROR))
                    }
                })
        }
    }

    private fun moveHomeActivity(user: User) =
        Intent(this, HomeActivity::class.java).apply {
            putExtra("USER", user)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }

    private fun signUpBtn() =
        binding.btnLoginNaviSignUp.setOnClickListener {
            moveSignUpActivity()
        }

    private fun moveSignUpActivity() =
        resultLauncher.launch(
            Intent(this, SignUpActivity::class.java).apply {
                this
            }
        )
}
