package com.tirexmurina.composerandomusr.domain.repository

import com.tirexmurina.composerandomusr.domain.entity.User

interface UsersRepository {

    suspend fun getUsers() : List<User>

    //suspend fun getUsers

    suspend fun getUsersBySeed(seedMap: Map<String, String>) : List<User>

    suspend fun clearDatabase()
}