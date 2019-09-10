package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
        "SELECT DISTINCT u.id, u.name, u.phone, u.created FROM users u " +
                " JOIN Assigned a ON (u.id = a.user_id)" +
                " WHERE  a.crate_id IS NOT NULL" +
                " AND (a.active = :active)"
    )
    fun getAssignedClients(active: Boolean): LiveData<List<UsersEntity>>


    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserById(userId: Long): LiveData<UsersEntity>


    @Query("DELETE FROM users WHERE phone = :phone")
    fun deleteUsers(phone: Int)

    @Query("SELECT * FROM users WHERE phone = :phone")
    fun getUserByPhone(phone: Int): UsersEntity?
}