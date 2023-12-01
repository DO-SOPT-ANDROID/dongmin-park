package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserDto
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserUserDto
import org.sopt.dosopttemplate.repository.UserRepository
import org.sopt.dosopttemplate.server.ServicePool
import retrofit2.Call
import retrofit2.Response

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _roadListResult = MutableLiveData<List<ResponseListUserUserDto>>()
    val roadListResult: LiveData<List<ResponseListUserUserDto>>
        get() = _roadListResult

    private val _roadListSuccess = MutableLiveData<Boolean>()
    val roadListSuccess: LiveData<Boolean>
        get() = _roadListSuccess

    suspend fun loadUserList() {
        userRepository.loadUser(2).onSuccess {
            _roadListResult.value = it
            _roadListSuccess.value = true
        }.onFailure {
            _roadListSuccess.value = false
        }


//        ServicePool.userService.getUserList(2).enqueue(
//            object : retrofit2.Callback<ResponseListUserDto> {
//                override fun onResponse(
//                    call: Call<ResponseListUserDto>,
//                    response: Response<ResponseListUserDto>
//                ) {
//                    if (response.isSuccessful) {
//                        _roadListResult.value = response.body()?.data
//                        _roadListSuccess.value = false
//                    } else {
//                        _roadListSuccess.value = false
//                    }
//                }
//
//                override fun onFailure(call: Call<ResponseListUserDto>, t: Throwable) {
//                    _roadListSuccess.value = false
//                }
//            }
//        )
    }
}