package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    fun login(
        @Body request: RequestLoginDto,
    ): Call<ResponseLoginDto>

    @POST("api/v1/members")
    fun signup(
        @Body request: RequestSignupDto,
    ): Call<Unit>
}
