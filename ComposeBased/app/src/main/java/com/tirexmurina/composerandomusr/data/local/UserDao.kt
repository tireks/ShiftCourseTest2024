package com.tirexmurina.composerandomusr.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.tirexmurina.composerandomusr.domain.entity.User


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getUserFromDatabase() : List<User>

    @Query("DELETE FROM user_table")
    suspend fun clearDatabase()

    @Insert(onConflict = REPLACE)
    suspend fun saveUserList(list: List<User>)

    @Query("SELECT COUNT(*) FROM user_table")
    suspend fun getTableSize() : Int

}