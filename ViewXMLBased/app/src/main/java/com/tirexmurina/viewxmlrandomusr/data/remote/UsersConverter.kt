package com.tirexmurina.viewxmlrandomusr.data.remote

import com.tirexmurina.viewxmlrandomusr.data.UsersModel
import com.tirexmurina.viewxmlrandomusr.domain.entity.User

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
                        location = it.location,
                        id = "$seedBuf|||${tempList.size + 1}" //создаю id внутри приложения, это костыль, но пока это лучший вариант
                    )
                )

            }

        }
        return tempList
    }

}