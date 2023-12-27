package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto

interface SignUpRepository {
    suspend fun signup(request: RequestSignupDto): Result<Unit>
}
