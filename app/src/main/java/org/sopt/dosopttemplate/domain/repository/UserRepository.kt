package org.sopt.dosopttemplate.domain.repository

import org.sopt.dosopttemplate.domain.entity.OtherUserList

interface UserRepository {
    suspend fun loadUser(page: Int): Result<OtherUserList>
}
