package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.LoginDataSource
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
) : LoginRepository {
    override suspend fun login(request: RequestLoginDto): Result<ResponseLoginDto> =
        runCatching {
            loginDataSource.login(request)
        }
}
