package com.tirexmurina.composerandomusr.domain.usecase

import com.tirexmurina.composerandomusr.domain.repository.UsersRepository
import javax.inject.Inject

interface IClearDatabaseUseCase{
    suspend operator fun invoke()
}

class ClearDatabaseUseCase @Inject constructor(
    private val repository: UsersRepository
) : IClearDatabaseUseCase{
    override suspend fun invoke() {
        repository.clearDatabase()
    }

}