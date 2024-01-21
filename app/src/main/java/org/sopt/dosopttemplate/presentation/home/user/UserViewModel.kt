package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.domain.entity.OtherUser
import org.sopt.dosopttemplate.domain.repository.UserRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _userState =
        MutableStateFlow<UiState<List<OtherUser>>>(UiState.Loading)
    val userState: StateFlow<UiState<List<OtherUser>>> get() = _userState

    suspend fun loadUserList() {
        viewModelScope.launch {
            userRepository.loadUser(2).onSuccess {
                _userState.value = UiState.Success(it)
            }.onFailure {
                _userState.value = UiState.Failure(it.message.toString())
            }
        }
    }
}
