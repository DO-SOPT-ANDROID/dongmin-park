package org.sopt.dosopttemplate.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.DoAndroidFragment
import org.sopt.dosopttemplate.LoginActivity
import org.sopt.dosopttemplate.MyPageFragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.utilprivate.makeToast

class HomeActivity : AppCompatActivity(), MyPageFragment.OnFragmentListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectFragemnt()
        clickBottomNavigation()
        setUser()
    }

    private fun connectFragemnt() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            binding.bnvHome.selectedItemId = R.id.menu_home

            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, HomeFragment())
                .commit()
        }
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.run {
            setOnItemReselectedListener { item ->
                when(item.itemId){
                    R.id.menu_home -> {
                        val homeFragment: HomeFragment = supportFragmentManager.findFragmentById(R.id.fcv_home) as HomeFragment
                        homeFragment.scrollToTop()
                        true
                    }

                    R.id.menu_do_android -> {
                        // do nothing
                        true
                    }

                    R.id.menu_mypage -> {
                        // do nothing
                        true
                    }

                    else -> false
                }
            }
        }

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

    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_home, fragment)
            .commit()


    private fun setUser() {
        lateinit var text: String

        try {
            getUserInfo()

            text = getString(R.string.LOGIN_SUCCESS)
        } catch (e: IllegalArgumentException) {
            moveLoginActivity()

            text = e.message.toString()
        } finally {
            makeToast(this, text)
        }
    }

    private fun getUserInfo() {
        userInfo =
            intent.getParcelable("USER", User::class.java) ?: throw IllegalArgumentException(
                "뭐에요. 내 user 정보 돌려줘요"
            )
    }

    override fun moveLoginActivity() =
        Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
}
