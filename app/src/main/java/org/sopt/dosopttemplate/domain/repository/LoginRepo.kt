package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto

interface LoginRepo {
    suspend fun login(dto: RequestLoginDto): Result<ResponseLoginDto>
}
