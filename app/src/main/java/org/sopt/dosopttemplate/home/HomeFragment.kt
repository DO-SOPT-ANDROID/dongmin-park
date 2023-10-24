package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.HumanAdapter
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
        humanAdapter.setHumanList(viewModel.mockFriendList)
    }
}


/*
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "FragmentHome 바인딩 객체가 생성되지 않았습니다" }

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val friendAdapter = HumanAdapter(requireContext())
        binding.rvFriends.adapter = friendAdapter
        friendAdapter.setHumanList(viewModel.mockFriendList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

 */