package com.invictusbytes.gemaries.ui.crates

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.data.repository.CratesRepository
import javax.inject.Inject

class CratesViewModel @Inject constructor(var cratesRepository: CratesRepository) :
    ViewModel() {
}