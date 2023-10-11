package org.sopt.dosopttemplate

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var intentToMainActivity: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentInfo()

        checkLoginAvailable()

        moveSignUpActivity()
    }

    private fun checkLoginAvailable() {
        binding.button.setOnClickListener {
            val ID = binding.editID.text.toString()
            val PW = binding.editTextTextPassword.text.toString()

            val isIdCorrect = isIDCorrect(ID)
            val isPwCorrect = isPWCorrect(PW)

            if (isIdCorrect && isPwCorrect) loginSuccessed()
            else loginFailed(isIdCorrect, isPwCorrect)
        }
    }

    private fun isIDCorrect(ID: String) = ID == intentToMainActivity.getStringExtra("ID")
    private fun isPWCorrect(PW: String) = PW == intentToMainActivity.getStringExtra("PW")

    private fun loginSuccessed() {
        makeToast("로그인 성공")

        startActivity(intentToMainActivity)
    }

    private fun loginFailed(isIdCorrect: Boolean, isPwCorrect: Boolean) {
        val text = if (!isIdCorrect) "ID가 잘못되었습니다"
        else if (!isPwCorrect) "PW가 잘못되었습니다"
        else "ERROR\n다시 시도해주세요"

        makeToast(text)
    }

    private fun moveSignUpActivity() {
        val intentToSignUpActivity = Intent(this, SignUpActivity::class.java)

        binding.textView6.setOnClickListener {
            resultLauncher.launch(intentToSignUpActivity)
        }
    }

    private fun getIntentInfo() {
        intentToMainActivity = Intent(this, MainActivity::class.java)
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val ID = result.data?.getStringExtra("ID") ?: ""
                    val PW = result.data?.getStringExtra("PW") ?: ""
                    val NICKNAME = result.data?.getStringExtra("NICKNAME") ?: ""
                    val MBTI = result.data?.getStringExtra("MBTI") ?: ""
                    val AboutMe = result.data?.getStringExtra("ABOUTME") ?: ""

                    binding.editID.setText(ID)
                    binding.editTextTextPassword.setText(PW)

                    intentToMainActivity.putExtra("ID", ID).putExtra("PW", PW)
                        .putExtra("NICKNAME", NICKNAME).putExtra("MBTI", MBTI)
                        .putExtra("ABOUTME", AboutMe)
                }
            }
    }

    private fun makeToast(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_SHORT
        ).show()
    }
}
