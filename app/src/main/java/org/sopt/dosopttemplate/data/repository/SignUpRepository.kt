package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.SignUpDataSource
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.domain.repository.SignUpRepo
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val signUpDataSource: SignUpDataSource,
) : SignUpRepo {
    override suspend fun signup(request: RequestSignupDto): Result<Unit> =
        runCatching {
            signUpDataSource.signup(request)
        }
}
