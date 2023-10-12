package org.sopt.dosopttemplate

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.utilprivate.makeToast
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signup()

        hideKeyboard()
    }

    private fun signup() {
        binding.btnSignupNaviLogin.setOnClickListener {
            user = with(binding) {
                User(
                    id = etvSignupId.text.toString(),
                    pw = etvSignupPw.text.toString(),
                    nickname = etvSignupNickname.text.toString(),
                    mbti = etvSignupMbti.text.toString().uppercase(),
                    aboutMe = etvSignupAboutMe.text.toString()
                )
            }

            if (allCorrect(user))
                signupSuccessed()
            else
                signupFailed(user)
        }
    }

    private fun allCorrect(user: User) = isIDCorrect(user.id.length) &&
            isPWCorrect(user.pw.length) &&
            isNotEmptyWithoutSpace(user.nickname) &&
            isMBTICorrect(user.mbti) &&
            isNotEmptyWithoutSpace(user.aboutMe)

    private fun isIDCorrect(len: Int) = len in 6..10
    private fun isPWCorrect(len: Int) = len in 8..12
    private fun isMBTICorrect(text: String) = MBTI_REGEX.matcher(text).find()
    private fun isNotEmptyWithoutSpace(text: String) = text.isNotBlank()

    private fun signupSuccessed() {
        intent.putExtra("USER", user)
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun signupFailed(
        user: User
    ) {
        val text = when {
            !isIDCorrect(user.id.length) -> ID_ERROR
            !isPWCorrect(user.pw.length) -> PW_ERROR
            !isNotEmptyWithoutSpace(user.nickname) -> NICKNAME_ERROR
            !isMBTICorrect(user.mbti) -> MBTI_ERROR
            !isNotEmptyWithoutSpace(user.aboutMe) -> ABOUT_ME_ERROR
            else -> DEFAULT_ERROR
        }

        makeToast(this, text)
    }

    companion object {
        private const val MBTI_PATTERN = "^[a-zA-Z]{4}\$"
        val MBTI_REGEX: Pattern = Pattern.compile(MBTI_PATTERN)

        private const val ID_ERROR = "ID를 6~10자 사이로 해주세요"
        private const val PW_ERROR = "PW를 8~12자 사이로 해주세요"
        private const val NICKNAME_ERROR = "닉네임을 공백 제외 1자 이상 해주세요"
        private const val MBTI_ERROR = "MBTI를 영문 4개로 설정해주세요"
        private const val ABOUT_ME_ERROR = "자기소개를 공백 제외 1자 이상 해주세요"
        private const val DEFAULT_ERROR = "ERROR\n다시 시도해주세요"
    }

    fun hideKeyboard() {
        binding.root.setOnClickListener {
            // 키보드 내리기
            val controller =
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            controller.hideSoftInputFromWindow(this.window.decorView.applicationWindowToken, 0)

            // 포커스 없애기
            val focus = currentFocus
            focus?.clearFocus()
        }
    }
}
