package com.invictusbytes.gemaries.ui.assigned_clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject

class AssignedClientsViewModel @Inject constructor(var usersRepository: UsersRepository) :
    ViewModel() {

    fun getAssignedClients(): LiveData<List<UsersEntity>> {
        return usersRepository.getAssignedClients()
    }

    fun deleteClient(user: UsersEntity) {
        usersRepository.deleteUser(user)
    }
}