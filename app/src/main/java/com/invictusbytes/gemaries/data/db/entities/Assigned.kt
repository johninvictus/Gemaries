package com.invictusbytes.gemaries.data.db.entities

import androidx.room.*
import java.util.*


@Entity(
    tableName = "Assigned",
    indices = [Index("user_id"), Index("crate_id")],
    foreignKeys = [
        ForeignKey(
            entity = UsersEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = CratesEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("crate_id"),
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Assigned(

    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,

    @ColumnInfo(name = "crate_id")
    val crateId: Long,

    @ColumnInfo(name = "user_id")
    val userId: Long,

    @ColumnInfo(name = "active")
    val active: Boolean,

    @ColumnInfo(name = "added")
    val added: Date,

    @ColumnInfo(name = "returned")
    val returned: Date
)