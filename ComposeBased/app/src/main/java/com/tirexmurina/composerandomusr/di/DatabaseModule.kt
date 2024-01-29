package com.tirexmurina.composerandomusr.di

import android.content.Context
import androidx.room.Room
import com.tirexmurina.composerandomusr.data.local.UserDao
import com.tirexmurina.composerandomusr.data.local.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        UserDatabase::class.java,
        "user_database"
    ).build()

    @Provides
    @Singleton
    fun provideDBDao(db: UserDatabase): UserDao {
        return db.userDao()
    }
}