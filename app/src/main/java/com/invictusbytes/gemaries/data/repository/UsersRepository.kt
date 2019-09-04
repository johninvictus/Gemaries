package com.invictusbytes.gemaries.data.repository

import androidx.lifecycle.LiveData
import com.invictusbytes.gemaries.data.db.dao.UsersDao
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import javax.inject.Inject

class UsersRepository @Inject constructor(var usersDao: UsersDao) {

    fun getUnAssignedClients(): LiveData<List<UsersEntity>>{
        return usersDao.getUnAssignedUsers(false)
    }
}