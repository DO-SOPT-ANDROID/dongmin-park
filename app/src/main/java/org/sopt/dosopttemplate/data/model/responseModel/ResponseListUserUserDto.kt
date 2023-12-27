package org.sopt.dosopttemplate.data.model.responseModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.OtherUser

@Serializable
data class ResponseListUserUserDto(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("first_name")
    val first_name: String,
    @SerialName("last_name")
    val last_name: String,
    @SerialName("avatar")
    val avatar: String,
)

fun ResponseListUserUserDto.toOtherUser() = OtherUser(
    email = email,
    first_name = first_name,
    last_name = last_name,
    avatar = avatar,
)
