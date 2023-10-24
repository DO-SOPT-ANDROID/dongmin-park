package org.sopt.dosopttemplate.Model

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
    ) : HumanModel()
}
