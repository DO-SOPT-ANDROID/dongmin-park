package org.sopt.dosopttemplate.data.service

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/members/sign-in")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): ResponseLoginDto

    @POST("api/v1/members")
    suspend fun signup(
        @Body request: RequestSignupDto,
    ): Unit
}
