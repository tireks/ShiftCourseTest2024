package com.tirexmurina.viewxmlrandomusr.domain.usecase

import com.tirexmurina.viewxmlrandomusr.domain.repository.UsersRepository
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