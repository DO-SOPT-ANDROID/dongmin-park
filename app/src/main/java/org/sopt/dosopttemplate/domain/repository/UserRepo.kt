package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.data.model.responseModel.ResponseListUserUserDto

interface UserRepo {
    suspend fun loadUser(page: Int): Result<List<ResponseListUserUserDto>>
}
