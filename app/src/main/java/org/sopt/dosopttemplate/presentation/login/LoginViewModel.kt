package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.User
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.presentation.signup.SignUpViewModel

class LoginViewModel(
    private val authRepository: LoginRepository,
) : ViewModel() {
    val isMoveSignupActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    val id = MutableLiveData<String>()
    val isIdValid: LiveData<Boolean> = id.map { isValidateId() }
    val pw = MutableLiveData<String>()
    val isPwValid: LiveData<Boolean> = pw.map { isValidatePw() }

    val buttonEnabled = MutableLiveData(false)

    private val _checkLoginUserState = MutableStateFlow<LoginState<User>>(LoginState.Empty)
    val checkLoginUserState: StateFlow<LoginState<User>> = _checkLoginUserState

    fun clickLoginBtn() {
        viewModelScope.launch {
            authRepository.login(
                RequestLoginDto(id.value ?: "", pw.value ?: ""),
            ).onSuccess {
                _checkLoginUserState.emit(
                    LoginState.Success(
                        User(id = it.id, username = it.username, nickname = it.nickname),
                    ),
                )
            }.onFailure {
                _checkLoginUserState.emit(LoginState.Error)
            }
        }
    }

    fun moveSignupActivity() {
        isMoveSignupActivity.value = true
    }

    fun checkValidation() {
        buttonEnabled.value = isValidateId() && isValidatePw()
    }

    private fun isValidateId() = SignUpViewModel.ID_REGEX.matcher(id.value.toString()).matches()

    private fun isValidatePw() = SignUpViewModel.PW_REGEX.matcher(pw.value.toString()).matches()
}
