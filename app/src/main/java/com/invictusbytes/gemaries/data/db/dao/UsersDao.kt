package com.invictusbytes.gemaries.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.invictusbytes.gemaries.data.db.entities.UsersEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(usersEntity: UsersEntity): Long


    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        "SELECT * FROM users u " +
                "LEFT JOIN Assigned a" +
                " ON u.id = a.user_id " +
                "WHERE a.crate_id IS NULL" +
                " OR a.active = :active" +
                " ORDER BY u.created DESC"
    )
    fun getUnAssignedUsers(active: Boolean): LiveData<List<UsersEntity>>
}