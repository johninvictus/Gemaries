package com.invictusbytes.gemaries.ui.unassigned

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class UnassignedViewModel @Inject constructor(var cratesRepository: CratesRepository) :
    ViewModel() {

    fun unAssignedCrates(): LiveData<List<CratesEntity>> {
        return cratesRepository.getUnAssignedCrates()
    }

    fun deleteCrate(crates: CratesEntity) {
        cratesRepository.deleteCrate(crates)
    }
}