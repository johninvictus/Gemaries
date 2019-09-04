package com.invictusbytes.gemaries.ui.assigned

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class AssignedViewModel @Inject constructor(var cratesRepository: CratesRepository) :
    ViewModel() {

    fun assignedCrates(): LiveData<List<CratesEntity>> {
        return cratesRepository.getAssignedCrates()
    }

    fun deleteCrate(crates: CratesEntity) {
        cratesRepository.deleteCrate(crates)
    }
}