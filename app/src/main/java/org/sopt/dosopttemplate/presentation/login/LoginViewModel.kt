package org.sopt.dosopttemplate.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.presentation.signup.SignUpViewModel

class LoginViewModel(
    private val authRepository: LoginRepository,
) : ViewModel() {
    val isMoveSignupActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

    fun setId(inputId: String) {
        id.value = inputId
    }

    fun setPw(inputPw: String) {
        pw.value = inputPw
    }

    val buttonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = checkValidation() }
        addSource(pw) { value = checkValidation() }
    }

    private val _loginResult = MutableLiveData<ResponseLoginDto>()
    val loginResult: LiveData<ResponseLoginDto>
        get() = _loginResult

    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

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
