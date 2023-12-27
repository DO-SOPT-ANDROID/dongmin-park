package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.service.AuthService
import javax.inject.Inject

class SignUpDataSourceImpl @Inject constructor(
    private val authService: AuthService,
) : SignUpDataSource {
    override suspend fun signup(request: RequestSignupDto): Unit = authService.signup(request)
}
