package org.sopt.dosopttemplate.domain.entity

data class OtherUserList(
    val otherUserList: List<OtherUser>,
) {
    data class OtherUser(
        val email: String,
        val first_name: String,
        val last_name: String,
        val avatar: String,
    )
}
