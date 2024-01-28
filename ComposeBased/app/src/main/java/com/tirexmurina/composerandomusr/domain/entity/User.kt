package com.tirexmurina.composerandomusr.domain.entity

import com.tirexmurina.composerandomusr.data.Info
import com.tirexmurina.composerandomusr.data.Location
import com.tirexmurina.composerandomusr.data.Name
import com.tirexmurina.composerandomusr.data.Picture

data class User(
    val cell: String,
    val email: String,
    val gender: String,
    val name: Name,
    val nat: String,
    val picture: Picture,
    val location: Location,
    val id: String
)
