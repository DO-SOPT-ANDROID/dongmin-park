package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto

interface AuthRepository {
    suspend fun login(dto: RequestLoginDto): Result<ResponseLoginDto>
    suspend fun signup(request: RequestSignupDto): Result<Unit>
}
