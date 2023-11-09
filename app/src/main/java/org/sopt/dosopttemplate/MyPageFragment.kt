package org.sopt.dosopttemplate

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding

class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {
    private lateinit var listener: OnFragmentListener
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPageBinding =FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setUserInfo()
        editProfileInfo()
    }

    private fun setListener(){
        listener = context as OnFragmentListener
    }

    private fun setUserInfo() =
        with(binding) {
            tvMainId.text = arguments?.getString(ARGS_ID)
            tvMainMbti.text = arguments?.getString(ARGS_MBTI)
            tvMainNickname.text = arguments?.getString(ARGS_NICKNAME)
            tvMainAboutMe.text = arguments?.getString(ARGS_ABOUTME)
        }

    private fun editProfileInfo(){
        binding.fabMyPageEditInfo.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog(){
        val builder = AlertDialog.Builder(this.context)
            .setTitle("로그아웃?")
            .setMessage("진짜?")
            .setPositiveButton("확인"){ dialog, which ->
                listener.moveLoginActivity()
            }
            .setNegativeButton("취소"){ dialog, which ->  }

        builder.show()
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

    interface OnFragmentListener {
        fun moveLoginActivity(): Intent
    }
}
