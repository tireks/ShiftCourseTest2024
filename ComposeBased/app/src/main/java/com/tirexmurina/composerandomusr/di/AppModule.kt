package com.tirexmurina.composerandomusr.di

import com.tirexmurina.composerandomusr.data.remote.UsersAPI
import com.tirexmurina.composerandomusr.domain.repository.UsersRepositoryImpl
import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import com.tirexmurina.composerandomusr.domain.usecase.ClearDatabaseUseCase
import com.tirexmurina.composerandomusr.domain.usecase.GetUsersBySeedUseCase
import com.tirexmurina.composerandomusr.domain.usecase.GetUsersUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IClearDatabaseUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersBySeedUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")  //not sure that it should be there. Maybe in repositoryImplementation?
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesUsersService(retrofit: Retrofit): UsersAPI {
        return retrofit.create(UsersAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt{
        @Binds
        @Singleton
        fun provideUsersRepository(repository: UsersRepositoryImpl) : UsersRepository

        @Binds
        @Singleton
        fun provideGetUsersUseCase(useCase: GetUsersUseCase) : IGetUsersUseCase

        @Binds
        @Singleton
        fun provideGetUsersBySeedUseCase(useCase: GetUsersBySeedUseCase) : IGetUsersBySeedUseCase

        @Binds
        @Singleton
        fun provideClearDatabaseUseCase(useCase: ClearDatabaseUseCase) : IClearDatabaseUseCase
    }
}