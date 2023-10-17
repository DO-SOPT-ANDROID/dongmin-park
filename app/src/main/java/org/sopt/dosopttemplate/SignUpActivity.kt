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
            !isIDCorrect(user.id.length) -> R.string.ID_ERROR.toString()
            !isPWCorrect(user.pw.length) -> R.string.PW_ERROR.toString()
            !isNotEmptyWithoutSpace(user.nickname) -> R.string.NICKNAME_ERROR.toString()
            !isMBTICorrect(user.mbti) -> R.string.MBTI_ERROR.toString()
            !isNotEmptyWithoutSpace(user.aboutMe) -> R.string.ABOUT_ME_ERROR.toString()
            else -> R.string.DEFAULT_ERROR.toString()
        }

        makeToast(this, text)
    }

    companion object {
        private const val MBTI_PATTERN = "^[a-zA-Z]{4}\$"
        val MBTI_REGEX: Pattern = Pattern.compile(MBTI_PATTERN)
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
