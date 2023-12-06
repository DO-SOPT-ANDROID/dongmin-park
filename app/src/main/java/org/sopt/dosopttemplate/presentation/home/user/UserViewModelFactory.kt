package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.ServicePool.userService
import org.sopt.dosopttemplate.data.repository.UserRepository

class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            val repository = UserRepository(userService)
            return UserViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}

// 아래 ViewModelFactory가 맞다면 삭제하도록 하겠습니다. 임시...저장용...
