package org.sopt.dosopttemplate.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.repository.SignUpRepository
import org.sopt.dosopttemplate.presentation.auth.AuthState
import java.util.regex.Pattern

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
) : ViewModel() {
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    private val _signUpState = MutableLiveData<AuthState>()
    val signUpState: LiveData<AuthState>
        get() = _signUpState

    fun checkValid() {
        val isIdError = !isValidateId() && !id.value.isNullOrBlank()
        val isPwError = !isValidatePw() && !pw.value.isNullOrBlank()
        val isDataValid = isValidateId() && isValidatePw() && isValidateNickname()

        _signUpState.value = AuthState(isIdError, isPwError, isDataValid)
    }

    fun clickSignupBtn() {
        viewModelScope.launch {
            signUpRepository.signup(
                RequestSignupDto(
                    id.value ?: "",
                    pw.value ?: "",
                    nickname.value ?: "",
                ),
            ).onSuccess {
                _signUpSuccess.value = true
            }.onFailure {
                _signUpSuccess.value = false
            }
        }
    }

    private fun isValidateId() = ID_REGEX.matcher(id.value.toString()).matches()

    private fun isValidatePw() = PW_REGEX.matcher(pw.value.toString()).matches()

    private fun isValidateNickname() = !nickname.value.isNullOrBlank()

    companion object {
        private const val idRegex = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        val ID_REGEX: Pattern = Pattern.compile(idRegex)

        private const val pwRegex =
            "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
        val PW_REGEX: Pattern = Pattern.compile(pwRegex)
    }
}
