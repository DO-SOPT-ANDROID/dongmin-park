package org.sopt.dosopttemplate.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.presentation.auth.AuthState
import org.sopt.dosopttemplate.presentation.auth.signup.SignUpViewModel

class LoginViewModel(
    private val authRepository: LoginRepository,
) : ViewModel() {
    // val isMoveSignupActivity: MutableLiveData<Boolean> = MutableLiveData(false)
    val isMoveSignupActivity: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    private val _loginState = MutableLiveData<AuthState>()
    val loginState: LiveData<AuthState>
        get() = _loginState

    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess
    val buttonEnabled = MutableStateFlow(false)
    // val buttonEnabled = MutableLiveData(false)

    fun checkValid() {
        val isIdError = !isValidateId() && !id.value.isNullOrBlank()
        val isPwError = !isValidatePw() && !pw.value.isNullOrBlank()
        val isDataValid = isValidateId() && isValidatePw()

        _loginState.value = AuthState(isIdError, isPwError, isDataValid)
    }

    fun setId(inputId: String) {
        id.value = inputId
    }

    fun setPw(inputPw: String) {
        pw.value = inputPw
    }

    fun clickLoginBtn() {
        viewModelScope.launch {
            authRepository.login(RequestLoginDto(id.value ?: "", pw.value ?: "")).onSuccess {
                _loginResult.value = it
                _loginSuccess.value = true
            }.onFailure {
                _loginSuccess.value = false
            }
        }
    }

    fun moveSignupActivity() {
        isMoveSignupActivity.value = true
    }

    fun isValidateId() = SignUpViewModel.ID_REGEX.matcher(id.value.toString()).matches()

    fun isValidatePw() = SignUpViewModel.PW_REGEX.matcher(pw.value.toString()).matches()

    private fun checkValidation() = isValidateId() && isValidatePw()
}
