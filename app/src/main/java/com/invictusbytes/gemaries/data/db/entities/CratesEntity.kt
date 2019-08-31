package com.invictusbytes.gemaries.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "Crates", indices = [Index("code")])
data class CratesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "created")
    val created: Date
)