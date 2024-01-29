package com.tirexmurina.composerandomusr.data.remote

import com.tirexmurina.composerandomusr.data.UsersModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface UsersAPI {

    @GET("api/?results=20&inc=gender,name,nat,email,cell,picture,nat,location")
    suspend fun getUsers() : UsersModel

    @GET("api/")
    suspend fun getUsersBySeed(
        @QueryMap seedMap : Map<String, String>
    ) : UsersModel
}