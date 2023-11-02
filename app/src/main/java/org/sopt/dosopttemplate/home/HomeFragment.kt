package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.HumanAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val humanAdapter = HumanAdapter(requireContext())
        binding.rvHumans.adapter = humanAdapter
        humanAdapter.addHumanList(viewModel.mockFriendList)
    }

    fun scrollToTop(){
        binding.rvHumans.smoothScrollToPosition(0)
    }
}
