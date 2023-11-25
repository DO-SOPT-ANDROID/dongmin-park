package org.sopt.dosopttemplate.model.requestModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignupDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String,
)
