package org.sopt.dosopttemplate.model.responseModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseListUserSupportDto(
    @SerialName("url")
    val url: String,
    @SerialName("text")
    val text: String
)
