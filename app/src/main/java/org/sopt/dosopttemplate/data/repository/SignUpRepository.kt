package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.service.AuthService

class SignUpRepository(
    private val authService: AuthService,
) {
    suspend fun signup(request: RequestSignupDto) =
        runCatching {
            authService.signup(request)
        }
}
