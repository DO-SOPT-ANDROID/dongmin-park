package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.service.AuthService

class AuthRepository(
    private val authService: AuthService,
) {
    suspend fun login(request: RequestLoginDto) =
        kotlin.runCatching {
            authService.login(request)
        }
}
