package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.dosopttemplate.base.BaseActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.home.HomeActivity
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.utilprivate.makeToast

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        getIntentInfo()

        checkLoginAvailable()

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
}
