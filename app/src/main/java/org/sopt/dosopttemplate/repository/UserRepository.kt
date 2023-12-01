package org.sopt.dosopttemplate.repository

import org.sopt.dosopttemplate.server.service.UserService

class UserRepository(
    private val userService: UserService
) {
    suspend fun loadUser(page: Int) =
        runCatching {
            userService.getUserList(page).data
        }
}