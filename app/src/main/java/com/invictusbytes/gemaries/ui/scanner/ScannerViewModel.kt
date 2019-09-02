package com.invictusbytes.gemaries.ui.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class ScannerViewModel @Inject constructor(var cratesRepository: CratesRepository) : ViewModel() {

    fun getCrate(code: String): CratesEntity? {
        return cratesRepository.getCrateByCode(code)
    }


    fun addCrate(crate: CratesEntity) {
        cratesRepository.addCrate(crate)
    }
}