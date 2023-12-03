package org.sopt.dosopttemplate.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.presentation.signup.SignUpViewModel
import org.sopt.dosopttemplate.server.ServicePool.authService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val isLoginButtonClicked: MutableLiveData<Boolean> = MutableLiveData(false)
    val isMoveSignupActivity: MutableLiveData<Boolean> = MutableLiveData(false)

    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()

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
        authService.login(RequestLoginDto(id.value ?: "", pw.value ?: ""))
            .enqueue(object : Callback<ResponseLoginDto> {
                override fun onResponse(
                    call: Call<ResponseLoginDto>,
                    response: Response<ResponseLoginDto>,
                ) {
                    if (response.isSuccessful) {
                        Log.e("TAG", "onResponse: 맞았다")
                        _loginResult.value = response.body()
                        _loginSuccess.value = true
                    } else {
                        Log.e("TAG", "onResponse: ㅡㅌㄹ렸다")
                        _loginSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                    _loginSuccess.value = false
                }
            })
    }

    fun moveSignupActivity() {
        isMoveSignupActivity.value = true
    }

    fun isValidateId() = SignUpViewModel.ID_REGEX.matcher(id.value).matches()

    fun isValidatePw() = SignUpViewModel.PW_REGEX.matcher(pw.value).matches()

    private fun checkValidation() = isValidateId() && isValidatePw()
}