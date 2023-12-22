package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.service.UserService

class UserRepository(
    private val userService: UserService,
) {
    suspend fun loadUser(page: Int) =
        runCatching {
            userService.getUserList(page).data
        }
}
