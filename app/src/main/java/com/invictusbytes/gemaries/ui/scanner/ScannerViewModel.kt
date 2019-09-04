package com.invictusbytes.gemaries.ui.scanner

import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.Assigned
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.AssignedRepository
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    var cratesRepository: CratesRepository,
    var assignedRepository: AssignedRepository
) : ViewModel() {

    fun getCrate(code: String): CratesEntity? {
        return cratesRepository.getCrateByCode(code)
    }


    fun addCrate(crate: CratesEntity) {
        cratesRepository.addCrate(crate)
    }

    fun getCrateIfAssigned(code: String): CratesEntity? {
        return cratesRepository.getCrateIfAssigned(code)
    }

    fun addAssigned(assigned: Assigned) {
        assignedRepository.addAssigned(assigned)
    }
}