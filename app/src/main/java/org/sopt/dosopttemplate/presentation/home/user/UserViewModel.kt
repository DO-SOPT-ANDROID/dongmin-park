package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.dosopttemplate.domain.entity.OtherUser
import org.sopt.dosopttemplate.domain.repository.UserRepo
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepo: UserRepo,
    // private val userRepository: UserRepository,
) : ViewModel() {
    private val _loadListResult = MutableLiveData<List<OtherUser>>()
    val loadListResult: LiveData<List<OtherUser>>
        get() = _loadListResult

    private val _loadListSuccess = MutableLiveData<Boolean>()
    val loadListSuccess: LiveData<Boolean>
        get() = _loadListSuccess

    suspend fun loadUserList() {
        userRepo.loadUser(2).onSuccess {
            _loadListResult.value = it
            _loadListSuccess.value = true
        }.onFailure {
            _loadListSuccess.value = false
        }
    }
}
