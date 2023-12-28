package org.sopt.dosopttemplate.presentation.auth

data class AuthState(
    val isIdError: Boolean,
    val isPwError: Boolean,
    val isDataValid: Boolean,
)
