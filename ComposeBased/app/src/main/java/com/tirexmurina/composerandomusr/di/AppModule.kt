package com.tirexmurina.composerandomusr.di

import com.tirexmurina.composerandomusr.data.UsersAPI
import com.tirexmurina.composerandomusr.data.UsersRepositoryImpl
import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import com.tirexmurina.composerandomusr.domain.usecase.GetUsersUseCase
import com.tirexmurina.composerandomusr.domain.usecase.IGetUsersUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
            .baseUrl("https://randomuser.me/api/")  //not sure that it should be there. Maybe in repositoryImplementation?
            //.addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesUsersService(retrofit: Retrofit): UsersAPI {
        return retrofit.create(UsersAPI::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt{
        @Binds
        @Singleton
        fun provideDishesRepository(repository: UsersRepositoryImpl) : UsersRepository

        @Binds
        @Singleton
        fun provideGetDishesUseCase(useCase: GetUsersUseCase) : IGetUsersUseCase
    }
}