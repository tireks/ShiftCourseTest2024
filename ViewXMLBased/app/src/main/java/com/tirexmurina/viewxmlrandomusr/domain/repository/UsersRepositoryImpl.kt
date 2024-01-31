package com.tirexmurina.viewxmlrandomusr.domain.repository

import com.tirexmurina.viewxmlrandomusr.data.local.UserDao
import com.tirexmurina.viewxmlrandomusr.data.remote.UsersAPI
import com.tirexmurina.viewxmlrandomusr.data.remote.UsersConverter
import com.tirexmurina.viewxmlrandomusr.domain.entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val dispatcher: CoroutineDispatcher,
    private val service: UsersAPI,
    private val converter: UsersConverter
) : UsersRepository {

    override suspend fun getUsers(): List<User>{
        return withContext(dispatcher){
            val response = try {
                if (dao.getTableSize() != 0){
                    throw Exception()
                }
                val hold = converter.convert(service.getUsers())
                dao.saveUserList(hold)
                hold
            } catch (e: Exception){
                dao.getUserFromDatabase()
            }
            response
        }
    }

    override suspend fun getUserById(id: String): User {
        return withContext(dispatcher){
            val response = try {
                if (dao.containsUserWithId(id)) {
                    throw Exception()
                }
                val seedMap = mapOf(
                    "results" to "20",
                    "inc" to "gender,name,nat,email,cell,picture,nat,location",
                    "seed" to id.substringBefore("|||")
                )
                val num = id.substringAfter("|||").toInt()
                val hold = converter.convert(service.getUsersBySeed(seedMap))[num]
                hold
            } catch (e: Exception){
                dao.getUserById(id)
            }
            response
        }
    }

    override suspend fun clearDatabase() {
        dao.clearDatabase()
    }


}