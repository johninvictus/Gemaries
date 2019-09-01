package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.invictusbytes.gemaries.data.db.entities.CratesEntity


@Dao
interface CratesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addCrate(cratesEntity: CratesEntity): Long


    @Query("SELECT * FROM Crates ")
    fun getAllCrates(): LiveData<List<CratesEntity>>


    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        "SELECT * FROM Crates INNER JOIN Assigned " +
                "ON Crates.id = Assigned.crate_id " +
                "AND Assigned.active = :active"
    )
    fun getAssignedCrates(active: Boolean): LiveData<List<CratesEntity>>


    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        "SELECT c.id, c.code, c.created FROM Crates c " +
                "LEFT JOIN  Assigned a " +
                "ON c.id = a.crate_id " +
                "WHERE a.crate_id IS NULL OR a.active = :active"
    )
    fun getUnAssignedCrates(active: Boolean): LiveData<List<CratesEntity>>

}