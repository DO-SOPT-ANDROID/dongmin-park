package org.sopt.dosopttemplate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.dosopttemplate.data.ServicePool
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.data.repository.SignUpRepository
import org.sopt.dosopttemplate.data.repository.UserRepository
import org.sopt.dosopttemplate.presentation.home.user.UserViewModel
import org.sopt.dosopttemplate.presentation.login.LoginViewModel
import org.sopt.dosopttemplate.presentation.signup.SignUpViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                val repository = SignUpRepository(ServicePool.authService)
                SignUpViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                val repository = LoginRepository(ServicePool.authService)
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                val repository = UserRepository(ServicePool.userService)
                UserViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}
