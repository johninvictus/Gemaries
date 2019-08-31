package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.invictusbytes.gemaries.data.db.entities.CratesEntity


@Dao
interface CratesDao{

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addCrate(cratesEntity: CratesEntity): Long


    @Query("SELECT * FROM Crates ")
    fun getAllCrates(): LiveData<List<CratesEntity>>

}