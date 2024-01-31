package com.tirexmurina.viewxmlrandomusr.domain.usecase

import com.tirexmurina.viewxmlrandomusr.domain.entity.User
import com.tirexmurina.viewxmlrandomusr.domain.repository.UsersRepository
import javax.inject.Inject

interface IGetUserByIdUseCase {

    suspend operator fun invoke(id: String) : User

}

class GetUserByIdUseCase @Inject constructor(
    private val repository: UsersRepository
) : IGetUserByIdUseCase {
    override suspend fun invoke(id: String): User {
        return repository.getUserById(id)
    }
}