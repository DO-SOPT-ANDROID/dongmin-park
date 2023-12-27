package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.OtherUser

interface UserRepo {
    suspend fun loadUser(page: Int): Result<List<OtherUser>>
}
