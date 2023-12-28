package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.dosopttemplate.domain.entity.OtherUserList
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _userState =
        MutableStateFlow<UiState<List<OtherUserList.OtherUser>>>(UiState.Loading)
    val userState: StateFlow<UiState<List<OtherUserList.OtherUser>>> get() = _userState

    suspend fun loadUserList() {
        userRepository.loadUser(2).onSuccess {
            _userState.value = UiState.Success(it.otherUserList)
        }.onFailure {
            _userState.value = UiState.Failure(it.message.toString())
        }
    }
}
