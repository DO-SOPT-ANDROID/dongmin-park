package org.sopt.dosopttemplate.server.service

import org.sopt.dosopttemplate.model.responseModel.ResponseListUserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users")
    suspend fun getUserList(
        @Query("page") page: Int,
    ): ResponseListUserDto
}
