package com.invictusbytes.gemaries.ui.unassign_crate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject

class UnassignViewModel @Inject constructor(var usersRepository: UsersRepository) : ViewModel() {

    fun getAssignedClients(): LiveData<List<UsersEntity>> {
        return usersRepository.getAssignedClients()
    }
}