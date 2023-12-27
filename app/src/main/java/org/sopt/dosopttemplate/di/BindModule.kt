package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.LoginDataSource
import org.sopt.dosopttemplate.data.datasource.LoginDataSourceImpl
import org.sopt.dosopttemplate.data.datasource.SignUpDataSource
import org.sopt.dosopttemplate.data.datasource.SignUpDataSourceImpl
import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.data.datasource.UserDataSourceImpl
import org.sopt.dosopttemplate.data.repository.LoginRepository
import org.sopt.dosopttemplate.data.repository.SignUpRepository
import org.sopt.dosopttemplate.data.repository.UserRepository
import org.sopt.dosopttemplate.domain.repository.LoginRepo
import org.sopt.dosopttemplate.domain.repository.SignUpRepo
import org.sopt.dosopttemplate.domain.repository.UserRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Binds
    @Singleton
    abstract fun bindUserDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    @Singleton
    abstract fun bindLoginDataSource(loginDataSourceImpl: LoginDataSourceImpl): LoginDataSource

    @Binds
    @Singleton
    abstract fun bindSignupDataSource(signUpDataSourceImpl: SignUpDataSourceImpl): SignUpDataSource

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepository: UserRepository): UserRepo

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepository: LoginRepository): LoginRepo

    @Binds
    @Singleton
    abstract fun bindSignupRepository(signupRepository: SignUpRepository): SignUpRepo
}
