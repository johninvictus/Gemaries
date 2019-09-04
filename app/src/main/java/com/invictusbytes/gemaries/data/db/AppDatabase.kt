package com.invictusbytes.gemaries.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.invictusbytes.gemaries.data.db.converters.DateTypeConverter
import com.invictusbytes.gemaries.data.db.dao.AssignedDao
import com.invictusbytes.gemaries.data.db.dao.CratesDao
import com.invictusbytes.gemaries.data.db.dao.UsersDao
import com.invictusbytes.gemaries.data.db.entities.Assigned
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity


@Database(
    entities = [
        UsersEntity::class, CratesEntity::class,
        Assigned::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
    abstract fun getCratesDao(): CratesDao
    abstract fun getAssignedDao(): AssignedDao
}