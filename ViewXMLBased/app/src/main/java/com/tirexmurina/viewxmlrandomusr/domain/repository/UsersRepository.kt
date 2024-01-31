package com.tirexmurina.viewxmlrandomusr.domain.repository

import com.tirexmurina.viewxmlrandomusr.domain.entity.User

interface UsersRepository {

    suspend fun getUsers() : List<User>

    suspend fun getUserById(id : String) : User

    suspend fun clearDatabase()
}