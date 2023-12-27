package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.OtherUserList

interface UserRepo {
    suspend fun loadUser(page: Int): Result<OtherUserList>
}
