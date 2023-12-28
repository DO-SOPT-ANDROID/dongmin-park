package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun login(request: RequestLoginDto): Result<ResponseLoginDto> =
        runCatching {
            authDataSource.login(request)
        }

    override suspend fun signup(request: RequestSignupDto): Result<Unit> =
        runCatching {
            authDataSource.signup(request)
        }
}
