package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.data.model.responseModel.toOtherUser
import org.sopt.dosopttemplate.domain.entity.OtherUser
import org.sopt.dosopttemplate.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {
    override suspend fun loadUser(page: Int): Result<List<OtherUser>> =
        runCatching {
            userDataSource.getUserList(page).toOtherUser()
        }
}
