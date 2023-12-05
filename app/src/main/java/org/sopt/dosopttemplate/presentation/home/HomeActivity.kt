package org.sopt.dosopttemplate.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.ActivityHomeBinding
import org.sopt.dosopttemplate.model.User
import org.sopt.dosopttemplate.presentation.home.doandroid.DoAndroidFragment
import org.sopt.dosopttemplate.presentation.home.mypage.MyPageFragment
import org.sopt.dosopttemplate.presentation.home.user.UserFragment
import org.sopt.dosopttemplate.presentation.login.LoginActivity
import org.sopt.dosopttemplate.util.getParcelable
import org.sopt.dosopttemplate.utilprivate.makeToast

class HomeActivity : AppCompatActivity(), MyPageFragment.OnFragmentListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userInfo: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectFragment()
        clickBottomNavigation()
        setUser()
    }

    private fun connectFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_home)
        if (currentFragment == null) {
            binding.bnvHome.selectedItemId = R.id.menu_home

            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_home, UserFragment())
                .commit()
        }
    }

    private fun clickBottomNavigation() {
        binding.bnvHome.run {
            setOnItemReselectedListener { item ->
                when (item.itemId) {
                    R.id.menu_home -> {
                        val userFragment: UserFragment =
                            supportFragmentManager.findFragmentById(R.id.fcv_home) as UserFragment
                        userFragment.scrollToTop()
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
                    replaceFragment(UserFragment())
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
                            username = userInfo.username,
                            nickname = userInfo.nickname,
                        ),
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

        runCatching {
            getUserInfo()
        }.fold(
            onSuccess = { text = getString(R.string.LOGIN_SUCCESS) + "\nuserid : ${userInfo.id}" },
            onFailure = { e ->
                moveLoginActivity()

                text = e.message.toString()
            },
        ).also {
            makeToast(this, text)
        }
    }

    private fun getUserInfo() {
        userInfo =
            intent.getParcelable("USER", User::class.java) ?: throw IllegalArgumentException(
                getString(R.string.USER_INFO_ERROR),
            )
    }

    override fun moveLoginActivity() =
        Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
}
