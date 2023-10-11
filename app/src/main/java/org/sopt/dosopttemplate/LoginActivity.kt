package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.utilprivate.makeToast

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentInfo()

        checkLoginAvailable()

        signUpBtn()
    }

    private fun getIntentInfo() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    user = result.data?.getParcelable("USER", User::class.java)
                        ?: return@registerForActivityResult
                    binding.etvLoginId.setText(user?.id)
                    binding.etvLoginPw.setText(user?.pw)
                }
            }
    }

    private fun checkLoginAvailable() {
        binding.btnLoginNaviLogIn.setOnClickListener {
            val ID = binding.etvLoginId.text.toString()
            val PW = binding.etvLoginPw.text.toString()

            if (!::user.isInitialized) {
                makeToast(this, "회원가입 안됨")
                return@setOnClickListener
            }

            val isIdCorrect = isIDCorrect(ID)
            val isPwCorrect = isPWCorrect(PW)

            if (isIdCorrect && isPwCorrect) loginSuccessed()
            else loginFailed(isIdCorrect, isPwCorrect)
        }
    }

    private fun isIDCorrect(ID: String) = user.id == ID
    private fun isPWCorrect(PW: String) = user.pw == PW

    private fun loginSuccessed() {
        // intent flag로 처리해줘야함.
        // 이 친구는 단방향(intent)를 사용할 때 처리해줘야 함.
        // ActivityResultLauncher<Intent>는 양방향이기 때문에 안쓰고, 그냥 finish 하면 됨.
        val intentToMainActivity = Intent(this, MainActivity::class.java)
        makeToast(this, "로그인 성공")
        intentToMainActivity.putExtra("USER", user)
        startActivity(intentToMainActivity)
    }

    private fun loginFailed(isIdCorrect: Boolean, isPwCorrect: Boolean) {
        val text = if (!isIdCorrect) "ID가 잘못되었습니다"
        else if (!isPwCorrect) "PW가 잘못되었습니다"
        else DEFAULT_ERROR

        makeToast(this, text)
    }

    private fun signUpBtn() {
        binding.btnLoginNaviSignUp.setOnClickListener {
            moveSignUpActivity()
        }
    }

    private fun moveSignUpActivity() {
        resultLauncher.launch(
            Intent(this, SignUpActivity::class.java).apply {
                this
            }
        )
    }

    companion object {
        private const val ID_ERROR = "ID를 6~10자 사이로 해주세요"
        private const val PW_ERROR = "PW를 8~12자 사이로 해주세요"
        private const val NICKNAME_ERROR = "닉네임을 공백 제외 1자 이상 해주세요"
        private const val MBTI_ERROR = "MBTI를 영문 4개로 설정해주세요"
        private const val ABOUT_ME_ERROR = "자기소개를 공백 제외 1자 이상 해주세요"
        private const val DEFAULT_ERROR = "ERROR\n다시 시도해주세요"
    }
}
