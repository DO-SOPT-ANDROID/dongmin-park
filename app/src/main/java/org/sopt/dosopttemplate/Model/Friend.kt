package org.sopt.dosopttemplate.Model

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val aboutMe: String,
)
