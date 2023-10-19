package org.sopt.dosopttemplate

import android.os.Bundle
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding
import org.sopt.dosopttemplate.utilprivate.makeToast
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignupBinding>({ActivitySignupBinding.inflate(it)}) {
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        signup()
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
            !isIDCorrect(user.id.length) -> getString(R.string.ID_ERROR)
            !isPWCorrect(user.pw.length) -> getString(R.string.PW_ERROR)
            !isNotEmptyWithoutSpace(user.nickname) -> getString(R.string.NICKNAME_ERROR)
            !isMBTICorrect(user.mbti) -> getString(R.string.MBTI_ERROR)
            !isNotEmptyWithoutSpace(user.aboutMe) -> getString(R.string.ABOUT_ME_ERROR)
            else -> getString(R.string.DEFAULT_ERROR)
        }

        makeToast(this, text)
    }

    companion object {
        private const val MBTI_PATTERN = "^[a-zA-Z]{4}\$"
        val MBTI_REGEX: Pattern = Pattern.compile(MBTI_PATTERN)
    }
}
