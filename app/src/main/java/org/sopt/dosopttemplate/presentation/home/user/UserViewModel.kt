package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserUserDto
import org.sopt.dosopttemplate.repository.UserRepository

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _loadListResult = MutableLiveData<List<ResponseListUserUserDto>>()
    val loadListResult: LiveData<List<ResponseListUserUserDto>>
        get() = _loadListResult

    private val _loadListSuccess = MutableLiveData<Boolean>()
    val loadListSuccess: LiveData<Boolean>
        get() = _loadListSuccess

    suspend fun loadUserList() {
        userRepository.loadUser(2).onSuccess {
            _loadListResult.value = it
            _loadListSuccess.value = true
        }.onFailure {
            _loadListSuccess.value = false
        }
    }
}
