package org.sopt.dosopttemplate.data.datasource

import org.sopt.dosopttemplate.data.model.requestModel.RequestSignupDto

interface SignUpDataSource {
    suspend fun signup(request: RequestSignupDto)
}
