package com.invictusbytes.gemaries.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.invictusbytes.gemaries.data.db.entities.UsersEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(usersEntity: UsersEntity): Long

}