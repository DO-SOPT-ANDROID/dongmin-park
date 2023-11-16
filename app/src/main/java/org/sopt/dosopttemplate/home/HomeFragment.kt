package org.sopt.dosopttemplate.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.ServicePool.userService
import org.sopt.dosopttemplate.UserAdapter
import org.sopt.dosopttemplate.UserViewModel
import org.sopt.dosopttemplate.base.BaseFragment
import org.sopt.dosopttemplate.databinding.FragmentHomeBinding
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserDto
import org.sopt.dosopttemplate.utilprivate.makeToast
import retrofit2.Call
import retrofit2.Response

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by activityViewModels<UserViewModel>()
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

        getUserList()

        viewModel.userList.observe(viewLifecycleOwner) {
            userAdapter.submitList(it)
        }
    }

    private fun getUserList() {

        userService.getUserList(2).enqueue(
            object : retrofit2.Callback<ResponseListUserDto> {
                override fun onResponse(
                    call: Call<ResponseListUserDto>,
                    response: Response<ResponseListUserDto>
                ) {
                    val userList = response.body()?.data ?: return
                    viewModel.setUserList(userList)
                }

                override fun onFailure(call: Call<ResponseListUserDto>, t: Throwable) {

                }
            }
        )
    }

    fun scrollToTop() {
        binding.rvHumans.smoothScrollToPosition(0)
    }
}
