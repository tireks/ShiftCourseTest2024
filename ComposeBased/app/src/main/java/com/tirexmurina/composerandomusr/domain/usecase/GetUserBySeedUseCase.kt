package com.tirexmurina.composerandomusr.domain.usecase

import com.tirexmurina.composerandomusr.domain.entity.User
import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import javax.inject.Inject

interface IGetUsersBySeedUseCase {

    suspend operator fun invoke(seedMap: Map<String, String>) : List<User>

}

class GetUsersBySeedUseCase @Inject constructor(
    private val repository: UsersRepository
) : IGetUsersBySeedUseCase {
    override suspend fun invoke(seedMap: Map<String, String>): List<User> {
        return repository.getUsersBySeed(seedMap)
    }
}