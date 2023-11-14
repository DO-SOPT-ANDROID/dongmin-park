package org.sopt.dosopttemplate.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.ServicePool.authService
import org.sopt.dosopttemplate.SignUpActivity
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.home.HomeActivity
import org.sopt.dosopttemplate.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.utilprivate.makeToast
import retrofit2.Call
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getIntentInfo()

        //checkLoginAvailable()
        login()
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
        user = result.data?.getParcelable("USER", User::class.java) ?: return

        binding.etvLoginId.setText(user.id)
        binding.etvLoginPw.setText(user.pw)
    }

    private fun checkLoginAvailable() {
        binding.btnLoginNaviLogIn.setOnClickListener {
            if (!::user.isInitialized) {
                makeToast(this, getString(R.string.LOGIN_SIGN_UP_ERROR))
                return@setOnClickListener
            }

            val id = binding.etvLoginId.text.toString()
            val pw = binding.etvLoginPw.text.toString()

            val isIdCorrect = isIDCorrect(id)
            val isPwCorrect = isPWCorrect(pw)

            if (isIdCorrect && isPwCorrect) loginSuccessed()
            else loginFailed(isIdCorrect, isPwCorrect)
        }
    }

    private fun isIDCorrect(ID: String) = user.id == ID
    private fun isPWCorrect(PW: String) = user.pw == PW

    private fun loginSuccessed() {
        moveHomeActivity()
        finish()
    }

    private fun moveHomeActivity() =
        Intent(this, HomeActivity::class.java).apply {
            putExtra("USER", user)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }

    private fun loginFailed(isIdCorrect: Boolean, isPwCorrect: Boolean) {
        val text = if (!isIdCorrect) getString(R.string.ID_ERROR)
        else if (!isPwCorrect) getString(R.string.PW_ERROR)
        else getString(R.string.DEFAULT_ERROR)

        makeToast(this, text)
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

    private fun login() {
        binding.btnLoginNaviLogIn.setOnClickListener{
            val id = binding.etvLoginId.text.toString()
            val password = binding.etvLoginPw.text.toString()

            authService.login(RequestLoginDto(id, password))
                .enqueue(object : retrofit2.Callback<ResponseLoginDto> {
                    override fun onResponse(
                        call: Call<ResponseLoginDto>,
                        response: Response<ResponseLoginDto>,
                    ) {
                        if (response.isSuccessful) {
                            val data: ResponseLoginDto = response.body()!!
                            val userId = data.id
                            makeToast(this@LoginActivity, "로그인이 성공하였고 유저의 ID는 $userId 입니둥")

                            moveHomeActivity()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                        makeToast(this@LoginActivity, "서버 에러 발생")
                    }
                })
        }
    }
}
