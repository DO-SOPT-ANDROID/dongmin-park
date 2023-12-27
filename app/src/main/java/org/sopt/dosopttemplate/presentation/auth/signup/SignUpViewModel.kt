package org.sopt.dosopttemplate.presentation.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.domain.repository.SignUpRepo
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepo: SignUpRepo,
) : ViewModel() {
    val id = MutableLiveData<String>()
    val isIdValid: LiveData<Boolean> = id.map { isValidateId() }
    val pw = MutableLiveData<String>()
    val isPwValid: LiveData<Boolean> = pw.map { isValidatePw() }
    val nickname = MutableLiveData<String>()
    val isNicknameValid: LiveData<Boolean> = nickname.map { isValidatePw() }

    val buttonEnabled = MutableLiveData(false)

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    fun clickSignupBtn() {
        viewModelScope.launch {
            signUpRepo.signup(
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

    private fun isValidateId() = ID_REGEX.matcher(id.value.toString()).matches()

    private fun isValidatePw() = PW_REGEX.matcher(pw.value.toString()).matches()

    private fun isValidateNickname() = !nickname.value.isNullOrBlank()

    fun checkValidation() {
        buttonEnabled.value = isValidateId() && isValidatePw() && isValidateNickname()
    }

    companion object {
        private const val idRegex = "(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}"
        val ID_REGEX: Pattern = Pattern.compile(idRegex)

        private const val pwRegex =
            "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*()])[a-zA-Z0-9!@#%^&*()]{6,12}"
        val PW_REGEX: Pattern = Pattern.compile(pwRegex)
    }
}
