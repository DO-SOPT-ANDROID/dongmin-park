package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : LoginDataSource {
    override suspend fun login(request: RequestLoginDto): ResponseLoginDto =
        authService.login(request)
}
