package org.sopt.dosopttemplate.presentation.login

sealed interface LoginState<out T> {
    object Error : LoginState<Nothing>
    object Empty : LoginState<Nothing>
    data class Success<T>(
        val data: T,
    ) : LoginState<T>
}
