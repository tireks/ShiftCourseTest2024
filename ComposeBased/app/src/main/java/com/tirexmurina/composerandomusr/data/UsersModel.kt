package com.tirexmurina.composerandomusr.data

import android.media.Session2Command

data class UsersModel(
    val info: Info,
    val results: List<Result>
)

data class Result(
    val cell: String,
    val email: String,
    val gender: String,
    val name: Name,
    val nat: String,
    val picture: Picture
)

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

data class Name(
    val first: String,
    val last: String,
    val title: String
)

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)