package com.invictusbytes.gemaries.ui.clients

import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject

class ClientsViewModel @Inject constructor(var usersRepository: UsersRepository) :
    ViewModel() {

    fun addClient(client: UsersEntity) {
        usersRepository.addUser(client)
    }

    fun getUserByPhone(phone: Int): UsersEntity? {
        return usersRepository.getUserByPhone(phone)
    }
}