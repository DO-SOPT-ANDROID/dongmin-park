package org.sopt.dosopttemplate.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.DoAndroidFragment
import org.sopt.dosopttemplate.MyPageFragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelable

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            binding.bnvHome.selectedItemId = R.id.menu_home

            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }

        clickBottomNavigation()
        getUserInfo()
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_do_android -> {
                    replaceFragment(DoAndroidFragment())
                    true
                }

                R.id.menu_mypage -> {
                    replaceFragment(
                        MyPageFragment.newInstance(
                            id = userInfo.id,
                            aboutMe = userInfo.aboutMe,
                            nickname = userInfo.nickname,
                            mbti = userInfo.mbti
                        )
                    )
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()
    }

    private fun getUserInfo() {
        userInfo = intent.getParcelable("USER", User::class.java) ?: throw IllegalArgumentException(
            "뭐에요. 내 user 정보 돌려줘요"
        )
    }
}
