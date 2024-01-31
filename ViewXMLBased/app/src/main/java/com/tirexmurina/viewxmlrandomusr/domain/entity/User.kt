package com.tirexmurina.viewxmlrandomusr.domain.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tirexmurina.viewxmlrandomusr.data.Location
import com.tirexmurina.viewxmlrandomusr.data.Name
import com.tirexmurina.viewxmlrandomusr.data.Picture

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