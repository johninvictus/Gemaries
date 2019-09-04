package com.invictusbytes.gemaries.ui.unassigned_clients

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import javax.inject.Inject


class UnAssignedClientsViewModel @Inject constructor() : ViewModel() {
    fun getUnAssignedClients(): LiveData<List<UsersEntity>> {

    }
}