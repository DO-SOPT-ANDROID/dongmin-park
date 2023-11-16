package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.sopt.dosopttemplate.ServicePool.userService
import org.sopt.dosopttemplate.UserAdapter
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserDto
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserUserDto
import retrofit2.Call
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModels<HomeViewModel>()

    private var mockUserList = listOf<ResponseListUserUserDto>()
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

//        val humanAdapter = HumanAdapter(requireContext())
//        binding.rvHumans.adapter = humanAdapter

        //val userAdapter = UserAdapter(requireContext())
        userAdapter = UserAdapter(requireContext())
        binding.rvHumans.adapter = userAdapter

        getUserList()

//
//        val handler = Handler() // 임시로 값 api 에서 들어오는지 확인하기 위한 delay. 삭제 예정
//        handler.postDelayed(Runnable {
//            userAdapter.setUserList(mockUserList)
//        }, 3000)

    }

    private fun getUserList() {
        userService.getUserList(2).enqueue(
            object : retrofit2.Callback<ResponseListUserDto> {
                override fun onResponse(
                    call: Call<ResponseListUserDto>,
                    response: Response<ResponseListUserDto>
                ) {
                    Log.e("TAG", "성공성공")
                    mockUserList = response.body()?.data?: return

                    userAdapter.submitList(mockUserList)
                }

                override fun onFailure(call: Call<ResponseListUserDto>, t: Throwable) {

                    Log.e("TAG", "실패실패")
                }
            }
        )
    }

    fun scrollToTop() {
        binding.rvHumans.smoothScrollToPosition(0)
    }
}
