package org.sopt.dosopttemplate.model

import androidx.annotation.DrawableRes

sealed class HumanModel {
    data class MyModel(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
    ) : HumanModel()

    data class FriendModel(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
        val useMelon: Boolean
    ) : HumanModel()

    data class FriendBirthdayModel(
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
        val useMelon: Boolean
    ) : HumanModel()

}
