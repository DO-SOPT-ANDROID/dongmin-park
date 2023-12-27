package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.entity.OtherUserList
import org.sopt.dosopttemplate.domain.repository.UserRepo
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepo,
) : ViewModel() {
    private val _loadListResult = MutableLiveData<List<OtherUserList.OtherUser>>()
    val loadListResult: LiveData<List<OtherUserList.OtherUser>>
        get() = _loadListResult

    private val _loadListSuccess = MutableLiveData<Boolean>()
    val loadListSuccess: LiveData<Boolean>
        get() = _loadListSuccess

    suspend fun loadUserList() {
        userRepo.loadUser(2).onSuccess {
            _loadListResult.value = it.otherUserList
            _loadListSuccess.value = true
        }.onFailure {
            _loadListSuccess.value = false
        }
    }
}
