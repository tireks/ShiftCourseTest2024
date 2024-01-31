package com.tirexmurina.viewxmlrandomusr.domain.usecase

import com.tirexmurina.viewxmlrandomusr.domain.entity.User
import com.tirexmurina.viewxmlrandomusr.domain.repository.UsersRepository
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