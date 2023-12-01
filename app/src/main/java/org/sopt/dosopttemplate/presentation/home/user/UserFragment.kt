package org.sopt.dosopttemplate.presentation.home.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.utilprivate.makeToast

class UserFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: UserViewModel by viewModels { UserViewModelFactory() }

    lateinit var userAdapter: UserAdapter

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(requireContext())
        binding.rvHumans.adapter = userAdapter

        observeList()
    }

    private fun observeList() {
        viewModel.roadListResult.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }

        viewModel.roadListSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                activity?.let { makeToast(it.baseContext, getString(R.string.LOGIN_SUCCESS)) }
            } else {
                activity?.let { makeToast(it.baseContext, getString(R.string.SERVER_ERROR)) }
            }
        }
    }

    fun scrollToTop() {
        binding.rvHumans.smoothScrollToPosition(0)
    }
}
