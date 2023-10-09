package org.sopt.dosopttemplate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signup()
    }

    private fun signup() {
        binding.button.setOnClickListener {
            val isIdCorrect = isIDCorrect(binding.editID.text.length)
            val isPwCorrect = isPWCorrect(binding.editTextTextPassword.text.length)
            val isNickNameCorrect = isNotEmptyWithoutSpace(binding.editNickname.text.toString())
            val isMBTICorrect = isMBTICorrect(binding.editMBTI.text.toString())
            val isAboutMeCorrect = isNotEmptyWithoutSpace(binding.editAboutMe.text.toString())

            if (isIdCorrect && isPwCorrect && isNickNameCorrect && isMBTICorrect && isAboutMeCorrect) signupSuccessed()
            else signupFailed(isIdCorrect, isPwCorrect, isNickNameCorrect, isMBTICorrect, isAboutMeCorrect)
        }
    }

    private fun isIDCorrect(len: Int) = len in 6..10
    private fun isPWCorrect(len: Int) = len in 8..12
    private fun isMBTICorrect(text: String) = Regex("^[a-zA-Z]{4}\$").matches(text) // 영어 4글자
    private fun isNotEmptyWithoutSpace(text: String) = text.replace(" ", "").isNotEmpty()

    private fun signupSuccessed() {
        intent.putExtra("ID", binding.editID.text.toString())
            .putExtra("PW", binding.editTextTextPassword.text.toString())
            .putExtra("NICKNAME", binding.editNickname.text.toString())
            .putExtra("MBTI", binding.editMBTI.text.toString().uppercase())
            .putExtra("ABOUTME", binding.editAboutMe.text.toString())

        setResult(RESULT_OK, intent)
        finish()
    }

    private fun signupFailed(
        isIDCorrect: Boolean,
        isPWCorrect: Boolean,
        isNicknameCorrect: Boolean,
        isMBTICorrect: Boolean,
        isAboutMeCorrect: Boolean
    ) {
        val text = if (!isIDCorrect) "ID를 6~10자 사이로 해주세요"
        else if (!isPWCorrect) "PW를 8~12자 사이로 해주세요"
        else if (!isNicknameCorrect) "닉네임을 공백 제외 1자 이상 해주세요"
        else if (!isMBTICorrect) "MBTI를 영문 4개로 설정해주세요"
        else if (!isAboutMeCorrect) "자기소개를 공백 제외 1자 이상 해주세요"
        else "ERROR\n다시 시도해주세요"

        makeToast(text)
    }

    private fun makeToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}