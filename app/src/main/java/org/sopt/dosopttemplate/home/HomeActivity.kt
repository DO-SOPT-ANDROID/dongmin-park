package org.sopt.dosopttemplate.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.DoAndroidFragment
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.MyPageFragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
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

    // 바텀 네비게이션을 클릭 했을 때? -> 사용자가 누른 아이템(메뉴의 아이템)에 따라 when문을 통해 해당하는 Fragment를 담아서 함수를 실행합니다.
    // true와 false는 리스너에게 Fragment 교체 작업을 정상적으로 처리했다는 것을 알려주기 위해서 작성합니다.
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

    // Activity에서 Fragment를 다뤄야 하니 supportFragmentManager를 사용합니다.
    // 트렌젝션을 시작하고 replace 메서드를 통해 Fragment를 교체합니다.
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