package org.sopt.dosopttemplate.presentation.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.repository.UserRepository
import org.sopt.dosopttemplate.server.ServicePool.userService

class UserViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) { //modelClass가 ExampleViewModel인지 확인
            val repository =
                UserRepository(userService)//dummyService를 인자로 받아서 Repository인스턴스 생성
            return UserViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}