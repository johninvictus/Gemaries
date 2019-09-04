package com.invictusbytes.gemaries.ui.client_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.db.entities.UsersEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import com.invictusbytes.gemaries.data.repository.UsersRepository
import javax.inject.Inject

class ClientProfileViewModel @Inject constructor(
    var usersRepository: UsersRepository,
    var cratesRepository: CratesRepository
) :
    ViewModel() {

    private var _userId = MutableLiveData<Long>()
    private var _user: LiveData<UsersEntity> = MutableLiveData()
    private var _crates: LiveData<List<CratesEntity>> = MutableLiveData()


    init {
        _user = Transformations.switchMap(_userId, usersRepository::getUserById)
        _crates = Transformations.switchMap(_userId, cratesRepository::getUserAssignedCrates)
    }

    fun setUserId(userId: Long) {
        if (_userId.value != userId) {
            _userId.value = userId
        }
    }

    fun getUser(): LiveData<UsersEntity> {
        return _user
    }

    fun getCrates(): LiveData<List<CratesEntity>> {
        return _crates
    }


}