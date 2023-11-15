package org.sopt.dosopttemplate

import org.sopt.dosopttemplate.model.responseModel.ResponseListUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("api/users")
    fun getUserList(
        @Query("page") page: Int
    ): Call<ResponseListUserDto>
}
