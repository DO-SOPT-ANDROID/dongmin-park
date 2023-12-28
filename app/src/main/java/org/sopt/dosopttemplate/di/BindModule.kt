package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.datasource.AuthDataSourceImpl
import org.sopt.dosopttemplate.data.datasource.UserDataSource
import org.sopt.dosopttemplate.data.datasource.UserDataSourceImpl
import org.sopt.dosopttemplate.data.repository.AuthRepositoryImpl
import org.sopt.dosopttemplate.data.repository.UserRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.AuthRepository
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
    abstract fun bindAuthDataSource(AuthDataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}
