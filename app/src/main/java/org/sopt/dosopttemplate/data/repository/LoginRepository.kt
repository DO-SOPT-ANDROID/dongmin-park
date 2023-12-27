package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.LoginDataSource
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.LoginRepo
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDataSource: LoginDataSource,
) : LoginRepo {
    override suspend fun login(request: RequestLoginDto): Result<ResponseLoginDto> =
        runCatching {
            loginDataSource.login(request)
        }
}
