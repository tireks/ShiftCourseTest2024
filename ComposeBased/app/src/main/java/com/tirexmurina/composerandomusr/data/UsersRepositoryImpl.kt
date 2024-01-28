package com.tirexmurina.composerandomusr.data

import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val service: UsersAPI
) : UsersRepository {
    private val converter = UsersConverter()
    /*override suspend fun getUsers(): UsersModel {
        return service.getUsers()
    }*/
    override suspend fun getUsers(): List<User> = converter.convert(service.getUsers())

    override suspend fun getUsersBySeed(seedMap: Map<String,String>): List<User> = converter.convert(service.getUsersBySeed(seedMap))

}