package org.sopt.dosopttemplate.presentation.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.LoginRepository
import org.sopt.dosopttemplate.presentation.auth.AuthState
import org.sopt.dosopttemplate.presentation.auth.signup.SignUpViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel() {
    val isMoveSignupActivity: MutableLiveData<Boolean> = MutableLiveData(false)

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
            loginRepository.login(RequestLoginDto(id.value ?: "", pw.value ?: "")).onSuccess {
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

    private fun isValidateId() = SignUpViewModel.ID_REGEX.matcher(id.value.toString()).matches()

    private fun isValidatePw() = SignUpViewModel.PW_REGEX.matcher(pw.value.toString()).matches()
}
