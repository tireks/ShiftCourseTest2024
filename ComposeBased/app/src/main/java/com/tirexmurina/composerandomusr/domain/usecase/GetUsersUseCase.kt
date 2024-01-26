package com.tirexmurina.composerandomusr.domain.usecase

import com.tirexmurina.composerandomusr.data.UsersModel
import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import javax.inject.Inject

interface IGetUsersUseCase {

    suspend operator fun invoke() : List<User>

}

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) : IGetUsersUseCase {
    override suspend fun invoke(): List<User> {
        return repository.getUsers()
    }

}