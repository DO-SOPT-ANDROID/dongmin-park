package org.sopt.dosopttemplate.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.repository.SignUpRepository
import java.util.regex.Pattern

class SignUpViewModel(
    private val signUpRepository: SignUpRepository,
) : ViewModel() {
    val id = MutableLiveData<String>()
    val isIdValid: LiveData<Boolean> = id.map { isValidateId() }
    val pw = MutableLiveData<String>()
    val isPwValid: LiveData<Boolean> = pw.map { isValidatePw() }
    val nickname = MutableLiveData<String>()
    val isNicknameValid: LiveData<Boolean> = nickname.map { isValidatePw() }

    val buttonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = checkValidation() }
        addSource(pw) { value = checkValidation() }
        addSource(nickname) { value = checkValidation() }
    }

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    fun clickSignupBtn() {
        viewModelScope.launch {
            signUpRepository.signup(
                RequestSignupDto(
                    id.value ?: "",
                    pw.value ?: "",
                    nickname.value ?: "",
                ),
            )
                .onSuccess {
                    _signUpSuccess.value = true
                }
                .onFailure {
                    _signUpSuccess.value = false
                }
        }
    }

    fun isValidateId() = ID_REGEX.matcher(id.value.toString()).matches()

    fun isValidatePw() = PW_REGEX.matcher(pw.value.toString()).matches()

    fun isValidateNickname() = !nickname.value.isNullOrBlank()

    private fun checkValidation() = isValidateId() && isValidatePw() && isValidateNickname()

    companion object {
        private const val idRegex = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        val ID_REGEX: Pattern = Pattern.compile(idRegex)

        private const val pwRegex =
            "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
        val PW_REGEX: Pattern = Pattern.compile(pwRegex)
    }
}
