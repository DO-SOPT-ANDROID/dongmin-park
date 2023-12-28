package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.responseModel.ResponseListUserDto

interface UserDataSource {
    suspend fun getUserList(page: Int): ResponseListUserDto
}
