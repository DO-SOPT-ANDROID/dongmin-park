package org.sopt.dosopttemplate.presentation.home.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.presentation.ViewModelFactory
import org.sopt.dosopttemplate.util.binding.BaseFragment
import org.sopt.dosopttemplate.utilprivate.makeToast

class UserFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: UserViewModel by viewModels { ViewModelFactory() }

    private var _userAdapter: UserAdapter? = null
    private val userAdapter
        get() = requireNotNull(_userAdapter) { getString(R.string.ADAPTER_ERROR) }

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        observeList()
        loadList()
    }

    private fun setAdapter() {
        _userAdapter = UserAdapter(requireContext())
        binding.rvHumans.adapter = userAdapter
    }

    private fun observeList() {
        viewModel.loadListSuccess.observe(viewLifecycleOwner) {
            if (!it) makeToast(requireContext(), getString(R.string.SERVER_ERROR))
        }

        viewModel.loadListResult.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }

    private fun loadList() {
        lifecycleScope.launch {
            viewModel.loadUserList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _userAdapter = null
    }

    fun scrollToTop() {
        binding.rvHumans.smoothScrollToPosition(0)
    }
}
