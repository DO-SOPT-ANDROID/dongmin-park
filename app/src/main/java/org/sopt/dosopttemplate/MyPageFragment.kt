package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding =FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
    }

    private fun setUserInfo() =
        with(binding) {
            tvMainId.text = arguments?.getString(ARGS_ID)
            tvMainMbti.text = arguments?.getString(ARGS_MBTI)
            tvMainNickname.text = arguments?.getString(ARGS_NICKNAME)
            tvMainAboutMe.text = arguments?.getString(ARGS_ABOUTME)
        }


    companion object {
        private const val ARGS_ID = "ID"
        private const val ARGS_MBTI = "MBTI"
        private const val ARGS_NICKNAME = "NICKNAME"
        private const val ARGS_ABOUTME = "ABOUTME"

        @JvmStatic
        fun newInstance(id: String, aboutMe: String, nickname: String, mbti: String) =
            MyPageFragment().apply {
                val args = bundleOf(
                    ARGS_ID to id,
                    ARGS_ABOUTME to aboutMe,
                    ARGS_NICKNAME to nickname,
                    ARGS_MBTI to mbti,
                )
                arguments = args
            }
    }
}
