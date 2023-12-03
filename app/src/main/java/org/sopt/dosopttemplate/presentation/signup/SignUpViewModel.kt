package org.sopt.dosopttemplate.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.server.ServicePool.authService
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()

    val buttonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(id) { value = checkValidation() }
        addSource(pw) { value = checkValidation() }
        addSource(nickname) { value = checkValidation() }
    }

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    fun clickSignupBtn() {
        authService.signup(RequestSignupDto(id.value ?: "", pw.value ?: "", nickname.value ?: ""))
            .enqueue(object : retrofit2.Callback<Unit> {
                override fun onResponse(
                    call: Call<Unit>,
                    response: Response<Unit>,
                ) {
                    _signUpSuccess.value = response.isSuccessful
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    _signUpSuccess.value = false
                }
            })
    }

    fun isValidateId() = id.value.isNullOrEmpty() || ID_REGEX.matcher(id.value).matches()

    fun isValidatePw() = pw.value.isNullOrEmpty() || PW_REGEX.matcher(pw.value).matches()

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