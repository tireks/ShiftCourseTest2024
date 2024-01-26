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
    val location: Location,
    val picture: Picture
)

data class Location (
    val street: Street,
    val city : String,
    val state : String,
    val country : String,
    val postcode : String,
    val coordinates : Coordinates,
    val timezone : Timezone
)

data class Street(
    val number: String,
    val name : String
)

data class Coordinates(
    val latitude: String,
    val longitude: String
)

data class Timezone(
    val offset : String,
    val description: String
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