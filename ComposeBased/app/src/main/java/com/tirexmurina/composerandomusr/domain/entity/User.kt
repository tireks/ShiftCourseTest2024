package com.tirexmurina.composerandomusr.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tirexmurina.composerandomusr.data.Info
import com.tirexmurina.composerandomusr.data.Location
import com.tirexmurina.composerandomusr.data.Name
import com.tirexmurina.composerandomusr.data.Picture

/*data class User(
    val cell: String,
    val email: String,
    val gender: String,
    val name: Name,
    val nat: String,
    val picture: Picture,
    val location: Location,
    val id: String
)*/

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val id: String,
    val cell: String,
    val email: String,
    val gender: String,
    @Embedded val name: Name,
    val nat: String,
    @Embedded val picture: Picture,
    @Embedded val location: Location
)
