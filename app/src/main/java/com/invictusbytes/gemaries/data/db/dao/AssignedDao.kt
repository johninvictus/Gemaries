package com.invictusbytes.gemaries.data.db.dao

import androidx.room.*
import com.invictusbytes.gemaries.data.db.entities.Assigned


@Dao
interface AssignedDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addEntry(assigned: Assigned)


    @Query(
        "SELECT * FROM Assigned a" +
                " WHERE a.user_id = :userId " +
                "AND a.crate_id = :crateId" +
                " AND a.active =:active "
    )
    fun getUserUnAssignedEntry(userId: Long, crateId: Long, active: Boolean): Assigned?

    @Update
    fun updateAssigned(assigned: Assigned)
}