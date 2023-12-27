package org.sopt.dosopttemplate.data.repository

import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.data.model.responseModel.toOtherUser
import org.sopt.dosopttemplate.domain.entity.OtherUser
import org.sopt.dosopttemplate.domain.repository.UserRepo
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepo {
    //    suspend fun loadUser(page: Int) =
//        runCatching {
//            userService.getUserList(page).data
//        }
    override suspend fun loadUser(page: Int): Result<List<OtherUser>> =
        runCatching {
            userDataSource.getUserList(page).data.map { it.toOtherUser() }
        }
}
