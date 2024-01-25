package com.tirexmurina.composerandomusr.data

import com.tirexmurina.composerandomusr.domain.entity.User

class UsersConverter {
    fun convert(from: UsersModel): MutableList<User>{
        val tempList: MutableList<User> = mutableListOf()
        val seedBuf = from.info.seed
        with(from.results) {
            forEach{
                tempList.add(
                    User(
                        cell = it.cell,
                        email = it.email,
                        gender = it.gender,
                        name = it.name,
                        nat = it.nat,
                        picture = it.picture,
                        seed = seedBuf
                    )
                )

            }

        }
        return tempList
    }

}