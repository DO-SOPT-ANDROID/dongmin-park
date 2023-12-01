package org.sopt.dosopttemplate.presentation.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.dosopttemplate.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.server.ServicePool.authService
import retrofit2.Call
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    val id = MutableLiveData<String>()
    val pw = MutableLiveData<String>()
    val nickname = MutableLiveData<String>()

    private val signupInfo = MutableLiveData<RequestSignupDto>()

    private val _signUpSuccess = MutableLiveData<Boolean>()
    val signUpSuccess: LiveData<Boolean>
        get() = _signUpSuccess

    fun clickSignupBtn() {
        signupInfo.value =
            RequestSignupDto(id.value.toString(), pw.value.toString(), nickname.value.toString())
        authService.signup(signupInfo.value ?: return)
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
}