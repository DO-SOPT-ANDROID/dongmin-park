package org.sopt.dosopttemplate.presentation.home.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.R
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.util.UiState
import org.sopt.dosopttemplate.util.binding.BaseFragment
import org.sopt.dosopttemplate.utilprivate.makeToast

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentHomeBinding>() {

    private val userViewModel: UserViewModel by viewModels()

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
        observeUserState()
        loadList()
    }

    private fun setAdapter() {
        _userAdapter = UserAdapter(requireContext())
        binding.rvHumans.adapter = userAdapter
    }

    private fun observeUserState() {
        userViewModel.userState.flowWithLifecycle(lifecycle).onEach { userState ->
            when (userState) {
                is UiState.Success -> {
                    userAdapter.submitList(userState.data)
                }

                is UiState.Failure -> {
                    makeToast(requireContext(), getString(R.string.SERVER_ERROR))
                }

                is UiState.Loading -> {
                    makeToast(requireContext(), getString(R.string.LOADING))
                }

                is UiState.Empty -> {
                    // 어... 친구가 없으시군요 풉
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun loadList() {
        lifecycleScope.launch {
            userViewModel.loadUserList()
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
