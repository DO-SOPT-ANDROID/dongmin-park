package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.util.getParcelable

class MainActivity : AppCompatActivity() {



    /*
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentInfo()
    }

    private fun getIntentInfo() {
        val user: User? = intent.getParcelable("USER", User::class.java)

        with(binding) {
            tvMainId.text = user?.id
            tvMainMbti.text = user?.mbti
            tvMainNickname.text = user?.nickname
            tvMainAboutMe.text = user?.aboutMe
        }
    }

     */
}
