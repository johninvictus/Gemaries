package com.invictusbytes.gemaries.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*


@Entity(
    tableName = "users",
    indices = [
        Index("name"),
        Index("phone")
    ]
)
data class UsersEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "phone")
    val phone: Int,

    @ColumnInfo(name = "created")
    val created: Date
)