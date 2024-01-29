package com.tirexmurina.composerandomusr.domain.repository

import android.util.Log
import com.tirexmurina.composerandomusr.data.UsersConverter
import com.tirexmurina.composerandomusr.data.local.UserDao
import com.tirexmurina.composerandomusr.data.remote.UsersAPI
import com.tirexmurina.composerandomusr.domain.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val dispatcher: CoroutineDispatcher,
    private val service: UsersAPI
) : UsersRepository {
    private val converter = UsersConverter()
    /*override suspend fun getUsers(): UsersModel {
        return service.getUsers()
    }*/
    //override suspend fun getUsers(): List<User> = converter.convert(service.getUsers())

    override suspend fun getUsers(): List<User>{
        return withContext(dispatcher){
            val response = try {
                if (dao.getTableSize() != 0){
                    Log.d("BK", "Table is not empty")
                    throw Exception()
                }
                val hold = converter.convert(service.getUsers())
                //dao.saveUserList(hold)
                hold
            } catch (e: Exception){
                dao.getUserFromDatabase()
            }
        response
        }
    }

    override suspend fun getUsersBySeed(seedMap: Map<String,String>): List<User> {
        return converter.convert(service.getUsersBySeed(seedMap))
    }

    override suspend fun clearDatabase() {
        dao.clearDatabase()
    }


}