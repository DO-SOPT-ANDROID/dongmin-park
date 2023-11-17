package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.responseModel.ResponseListUserUserDto

class UserViewModel : ViewModel() {
    private val _userList = MutableLiveData<List<ResponseListUserUserDto>>()
    val userList: LiveData<List<ResponseListUserUserDto>> = _userList

    fun setUserList(test: List<ResponseListUserUserDto>) {
        _userList.value = test.toList()
    }
}