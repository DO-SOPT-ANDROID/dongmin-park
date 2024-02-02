package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : AuthDataSource {
    override suspend fun login(request: RequestLoginDto): ResponseLoginDto =
        authService.postLogin(request)

    override suspend fun signup(request: RequestSignupDto): Unit = authService.postSignup(request)
}
