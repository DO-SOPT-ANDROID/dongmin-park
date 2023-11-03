package org.sopt.dosopttemplate.model

import androidx.annotation.DrawableRes

sealed class HumanModel {
    abstract val userId: Int

    data class MyModel(
        override val userId: Int,
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
    ) : HumanModel()

    data class FriendModel(
        override val userId: Int,
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
        val useMelon: Boolean
    ) : HumanModel()

    data class FriendBirthdayModel(
        override val userId: Int,
        @DrawableRes
        val profileImage: Int,
        val name: String,
        val aboutMe: String,
        val useMelon: Boolean
    ) : HumanModel()
}
