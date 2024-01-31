package com.tirexmurina.viewxmlrandomusr.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tirexmurina.viewxmlrandomusr.domain.entity.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    suspend fun getUserFromDatabase() : List<User>

    @Query("DELETE FROM user_table")
    suspend fun clearDatabase()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserList(list: List<User>)

    @Query("SELECT COUNT(*) FROM user_table")
    suspend fun getTableSize() : Int

    @Query("SELECT * FROM user_table WHERE id = :id")
    suspend fun getUserById(id: String) : User

    @Query("SELECT EXISTS (SELECT 1 FROM user_table WHERE id = :id)")
    suspend fun containsUserWithId(id : String) : Boolean
}