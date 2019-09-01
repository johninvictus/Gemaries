package com.invictusbytes.gemaries.ui.crates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class CratesViewModel @Inject constructor(var cratesRepository: CratesRepository) :
    ViewModel() {

    fun addCrate(crate: CratesEntity) {
        cratesRepository.addCrate(crate)
    }


    fun allCrate(): LiveData<List<CratesEntity>> {
        return cratesRepository.getAllCrates()
    }

    fun assignedCrates(): LiveData<List<CratesEntity>> {
        return cratesRepository.getAssignedCrates()
    }

    fun unAssignedCrates(): LiveData<List<CratesEntity>> {
        return cratesRepository.getUnAssignedCrates()
    }
}