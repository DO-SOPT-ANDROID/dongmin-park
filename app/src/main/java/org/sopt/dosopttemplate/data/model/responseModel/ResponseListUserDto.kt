package org.sopt.dosopttemplate.data.model.responseModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.dosopttemplate.domain.entity.OtherUser

@Serializable
data class ResponseListUserDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<UserDto>,
    @SerialName("support")
    val support: SupportDto,
) {
    @Serializable
    data class SupportDto(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    @Serializable
    data class UserDto(
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
}

fun ResponseListUserDto.toOtherUser(): List<OtherUser> = data.map {
    OtherUser(
        email = it.email,
        first_name = it.first_name,
        last_name = it.last_name,
        avatar = it.avatar,
    )
}
