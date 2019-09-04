package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(usersEntity: UsersEntity): Long


    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UsersEntity>>


    @Query(
        "SELECT * FROM users u " +
                "LEFT JOIN Assigned a" +
                " ON u.id = a.user_id " +
                "WHERE a.crate_id IS NULL" +
                " OR a.active = :active" +
                " ORDER BY u.created DESC"
    )
    fun getUnAssignedUsers(active: Boolean): LiveData<List<UsersEntity>>

    @Query(
        "SELECT * FROM users u " +
                "INNER JOIN Assigned a " +
                "ON u.id = a.user_id " +
                "AND a.active = :active"
    )
    fun getAssignedClients(active: Boolean): LiveData<List<UsersEntity>>


    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Long): LiveData<UsersEntity>
}