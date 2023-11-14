package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.login.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.login.responseModel.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>
}
