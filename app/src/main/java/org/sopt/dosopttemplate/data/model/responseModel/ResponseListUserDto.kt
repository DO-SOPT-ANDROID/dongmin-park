package org.sopt.dosopttemplate.data.model.responseModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    val data: List<ResponseListUserUserDto>,
    @SerialName("support")
    val support: ResponseListUserSupportDto,
)
