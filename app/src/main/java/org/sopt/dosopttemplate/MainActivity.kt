package org.sopt.dosopttemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import org.sopt.dosopttemplate.databinding.ActivityLoginBinding
import org.sopt.dosopttemplate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentInfo()
    }

    private fun getIntentInfo() {
        binding.tvMainId.text = intent.getStringExtra("ID")
        binding.tvMainMbti.text = intent.getStringExtra("MBTI")
        binding.tvMainNickname.text = intent.getStringExtra("NICKNAME")
        binding.tvMainAboutMe.text = intent.getStringExtra("ABOUTME")
    }
}
