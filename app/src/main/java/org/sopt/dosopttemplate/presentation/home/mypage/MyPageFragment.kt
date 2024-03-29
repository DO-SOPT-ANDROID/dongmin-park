package org.sopt.dosopttemplate.presentation.home.mypage

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentMyPageBinding
import org.sopt.dosopttemplate.util.binding.BaseFragment
import org.sopt.dosopttemplate.utilprivate.makeToast

class MyPageFragment : BaseFragment<FragmentMyPageBinding>() {
    private lateinit var moveLoginListener: OnFragmentListener
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMyPageBinding = FragmentMyPageBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setUserInfo()
        logoutBtn()
    }

    private fun setListener() {
        moveLoginListener = context as OnFragmentListener
    }

    private fun setUserInfo() =
        with(binding) {
            tvMainId.text = arguments?.getString(ARGS_ID)
            tvMainUserName.text = arguments?.getString(ARGS_USERNAME)
            tvMainNickname.text = arguments?.getString(ARGS_NICKNAME)
        }

    private fun logoutBtn() {
        binding.fabMyPageLogout.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val builder = AlertDialog.Builder(this.context)
            .setTitle(R.string.LOGOUT)
            .setMessage(R.string.LOGOUT_MESSAGE)
            .setPositiveButton(R.string.LOGOUT) { dialog, which ->
                makeToast(this.requireContext(), getString(R.string.LOGOUT_SUCCESS))
                moveLoginListener.moveLoginActivity()
            }
            .setNegativeButton(R.string.CANCEL) { dialog, which -> }

        builder.show()
    }

    companion object {
        private const val ARGS_ID = "ID"
        private const val ARGS_USERNAME = "USERNAME"
        private const val ARGS_NICKNAME = "NICKNAME"

        @JvmStatic
        fun newInstance(id: Int, username: String, nickname: String) =
            MyPageFragment().apply {
                val args = bundleOf(
                    ARGS_ID to id.toString(),
                    ARGS_USERNAME to username,
                    ARGS_NICKNAME to nickname,
                )
                arguments = args
            }
    }

    interface OnFragmentListener {
        fun moveLoginActivity(): Intent
    }
}
