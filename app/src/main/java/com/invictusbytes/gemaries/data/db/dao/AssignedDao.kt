package com.invictusbytes.gemaries.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.invictusbytes.gemaries.data.db.entities.Assigned


@Dao
interface AssignedDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addEntry(assigned: Assigned)
}