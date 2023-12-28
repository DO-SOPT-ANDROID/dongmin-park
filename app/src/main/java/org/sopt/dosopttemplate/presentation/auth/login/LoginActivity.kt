package org.sopt.dosopttemplate.presentation.auth.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.data.model.User
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.presentation.auth.signup.SignUpActivity
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.utilprivate.makeToast

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        getIntentInfo()
        observeLoginResult()
        observeMoveSignupActivity()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel
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
        val id = result.data?.getStringExtra("ID") ?: return
        val pw = result.data?.getStringExtra("PW") ?: return

        loginViewModel.setId(id)
        loginViewModel.setPw(pw)
    }

    private fun observeLoginResult() {
        loginViewModel.loginSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val data: ResponseLoginDto = loginViewModel.loginResult.value ?: return@observe
                val userId = data.id
                val username = data.username
                val nickname = data.nickname
                val user = User(id = userId, username = username, nickname = nickname)

                moveHomeActivity(user)
            } else {
                makeToast(this@LoginActivity, getString(R.string.SERVER_ERROR))
            }
        }
    }

    private fun moveHomeActivity(user: User) =
        Intent(this, HomeActivity::class.java).apply {
            putExtra("USER", user)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
            finish()
        }

    private fun observeMoveSignupActivity() {
        loginViewModel.isMoveSignupActivity.observe(this) {
            if (it) {
                resultLauncher.launch(
                    Intent(this, SignUpActivity::class.java).apply {
                        this
                    },
                )
            }
        }
    }
}
