package com.invictusbytes.gemaries.data.repository

import androidx.lifecycle.LiveData
import com.invictusbytes.gemaries.data.db.dao.UsersDao
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.utils.AppExecutors
import javax.inject.Inject

class UsersRepository @Inject constructor(var usersDao: UsersDao, var appExecutors: AppExecutors) {


    fun addUser(usersEntity: UsersEntity) {
        appExecutors.diskIO().execute {
            usersDao.addUser(usersEntity)
        }
    }

    fun getUserById(userId: Long): LiveData<UsersEntity> {
        return usersDao.getUserById(userId)
    }

    fun getAllUsers(): LiveData<List<UsersEntity>> {
        return usersDao.getAllUsers()
    }

    fun getUnAssignedClients(): LiveData<List<UsersEntity>> {
        return usersDao.getUnAssignedUsers(false)
    }

    fun getAssignedClients(): LiveData<List<UsersEntity>> {
        return usersDao.getAssignedClients(true)
    }

    fun deleteUser(user: UsersEntity) {
        usersDao.deleteUsers(user.phone)
    }

    fun getUserByPhone(phone: Int): UsersEntity? {
        return usersDao.getUserByPhone(phone)
    }
}