package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.invictusbytes.gemaries.data.db.entities.CratesEntity


@Dao
interface CratesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addCrate(cratesEntity: CratesEntity): Long


    @Query("SELECT * FROM Crates WHERE code = :code")
    fun getCrateByCode(code: String): CratesEntity?


    @Query("SELECT * FROM Crates ")
    fun getAllCrates(): LiveData<List<CratesEntity>>


    @Query(
        "SELECT * FROM Crates INNER JOIN Assigned " +
                "ON Crates.id = Assigned.crate_id " +
                "AND Assigned.active = :active"
    )
    fun getAssignedCrates(active: Boolean): LiveData<List<CratesEntity>>


    @Query(
        "SELECT c.id, c.code, c.created FROM Crates c " +
                "LEFT JOIN  Assigned a " +
                "ON c.id = a.crate_id " +
                "WHERE a.crate_id IS NULL OR a.active = :active " +
                "ORDER BY c.created DESC"
    )
    fun getUnAssignedCrates(active: Boolean): LiveData<List<CratesEntity>>


    @Query(
        "SELECT c.id, c.code, c.created FROM Crates c" +
                " LEFT JOIN Assigned a " +
                "ON (c.id = a.crate_id) " +
                "WHERE (a.user_id = :userId)" +
                " AND (a.active = :active)"
    )
    fun getUserAssignedCrates(userId: Long, active: Boolean): LiveData<List<CratesEntity>>


    @Query(
        "SELECT * FROM Crates c " +
                "LEFT JOIN Assigned a " +
                "ON c.id = a.crate_id " +
                "WHERE a.active = :active " +
                "AND c.code = :code"
    )
    fun getCrateIfAssigned(active: Boolean, code: String): CratesEntity?


    @Query(
        "SELECT * FROM Crates c" +
                " LEFT JOIN Assigned a " +
                "ON c.id = a.crate_id " +
                "WHERE (a.active = :active)" +
                " AND (c.code = :code) " +
                "AND (a.user_id = :userId)"
    )
    fun getCrateIfAssignedToUser(active: Boolean, code: String, userId: Long): CratesEntity?


    @Delete
    fun deleteCrates(vararg cratesEntity: CratesEntity)
}