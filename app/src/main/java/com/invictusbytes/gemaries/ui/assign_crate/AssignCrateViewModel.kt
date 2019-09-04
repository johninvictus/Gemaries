package com.invictusbytes.gemaries.ui.assign_crate

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject

class AssignCrateViewModel @Inject constructor(var usersRepository: UsersRepository) :
    ViewModel() {

    fun getAllClients(): LiveData<List<UsersEntity>> {
        return usersRepository.getAllUsers()
    }
}