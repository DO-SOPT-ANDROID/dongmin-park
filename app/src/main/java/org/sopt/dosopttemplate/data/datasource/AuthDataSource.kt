package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.requestModel.RequestLoginDto
import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto
import org.sopt.dosopttemplate.data.model.responseModel.ResponseLoginDto

interface AuthDataSource {
    suspend fun login(request: RequestLoginDto): ResponseLoginDto
    suspend fun signup(request: RequestSignupDto)
}
