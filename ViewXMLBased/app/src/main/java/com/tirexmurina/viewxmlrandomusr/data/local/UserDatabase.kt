package com.tirexmurina.viewxmlrandomusr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tirexmurina.viewxmlrandomusr.domain.entity.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
}