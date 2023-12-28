package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.responseModel.ResponseListUserDto
import org.sopt.dosopttemplate.data.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
) : UserDataSource {
    override suspend fun getUserList(page: Int): ResponseListUserDto = userService.getUserList(page)
}
