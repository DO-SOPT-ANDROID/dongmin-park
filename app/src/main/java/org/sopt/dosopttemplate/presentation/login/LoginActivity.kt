package org.sopt.dosopttemplate.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.presentation.home.HomeActivity
import org.sopt.dosopttemplate.presentation.signup.SignUpActivity
import org.sopt.dosopttemplate.utilprivate.makeToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val authViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        getIntentInfo()
        observeIdCorrect()
        observePwCorrect()
        observeLoginResult()
        observeIsSignUpValid()
        observeMoveSignupActivity()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.loginViewModel = authViewModel
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

        authViewModel.id.value = id
        authViewModel.pw.value = pw
    }

    private fun observeIdCorrect() {
        authViewModel.id.observe(this) {
            binding.etvLoginId.error =
                if (authViewModel.isValidateId() || authViewModel.id.value.isNullOrBlank()) {
                    null
                } else {
                    getString(R.string.ID_ERROR)
                }
        }
    }

    private fun observePwCorrect() {
        authViewModel.pw.observe(this) {
            binding.etvLoginPw.error =
                if (authViewModel.isValidatePw() || authViewModel.pw.value.isNullOrBlank()) {
                    null
                } else {
                    getString(R.string.PW_ERROR)
                }
        }
    }

    private fun observeIsSignUpValid() {
        authViewModel.buttonEnabled.observe(this) {
            binding.btnLoginNaviLogIn.isEnabled = it
        }
    }

    private fun observeLoginResult() {
        authViewModel.loginSuccess.observe(this) { isSuccess ->
            if (isSuccess) {
                val data: ResponseLoginDto = authViewModel.loginResult.value ?: return@observe
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
        }

    private fun observeMoveSignupActivity() {
        authViewModel.isMoveSignupActivity.observe(this) {
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
