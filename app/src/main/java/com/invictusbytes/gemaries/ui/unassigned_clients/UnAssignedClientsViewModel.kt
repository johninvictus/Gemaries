package com.invictusbytes.gemaries.ui.unassigned_clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject


class UnAssignedClientsViewModel @Inject constructor(var usersRepository: UsersRepository) :
    ViewModel() {
    fun getUnAssignedClients(): LiveData<List<UsersEntity>> {
        return usersRepository.getUnAssignedClients()
    }

    fun deleteClient(user: UsersEntity) {
        usersRepository.deleteUser(user)
    }
}