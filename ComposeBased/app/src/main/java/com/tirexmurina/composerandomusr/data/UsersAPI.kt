package com.tirexmurina.composerandomusr.data

import retrofit2.http.GET

interface UsersAPI {
    @GET("?results=20&inc=gender,name,nat,email,cell,picture,nat,location")
    suspend fun getUsers() : UsersModel
}