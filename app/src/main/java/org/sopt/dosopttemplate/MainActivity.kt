package org.sopt.dosopttemplate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.dosopttemplate.Model.User
import org.sopt.dosopttemplate.databinding.ActivityMainBinding
import org.sopt.dosopttemplate.util.getParcelable

class MainActivity : BaseActivity<ActivityMainBinding>({ActivityMainBinding.inflate(it)}){
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
