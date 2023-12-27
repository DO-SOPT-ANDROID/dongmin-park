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
import org.sopt.dosopttemplate.data.repository.LoginRepositoryImpl
import org.sopt.dosopttemplate.data.repository.SignUpRepositoryImpl
import org.sopt.dosopttemplate.data.repository.UserRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.LoginRepository
import org.sopt.dosopttemplate.domain.repository.SignUpRepository
import org.sopt.dosopttemplate.domain.repository.UserRepository
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
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun bindSignupRepository(signupRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}
