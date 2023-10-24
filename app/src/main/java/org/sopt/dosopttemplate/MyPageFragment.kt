package org.sopt.dosopttemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null

    private val binding: FragmentMyPageBinding
        get() = requireNotNull(_binding) { "FragmentMyPage 바인딩 객체가 생성되지 않았습니다" }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUserInfo() {
        with(binding) {
            tvMainId.text = arguments?.getString(ARGS_ID)//user?.id
            tvMainMbti.text = arguments?.getString(ARGS_MBTI)
            tvMainNickname.text = arguments?.getString(ARGS_NICKNAME)
            tvMainAboutMe.text = arguments?.getString(ARGS_ABOUTME)
        }
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