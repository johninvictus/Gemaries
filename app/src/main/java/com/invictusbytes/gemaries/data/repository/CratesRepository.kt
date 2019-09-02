package com.invictusbytes.gemaries.data.repository

import androidx.lifecycle.LiveData
import com.invictusbytes.gemaries.data.db.dao.CratesDao
import com.invictusbytes.gemaries.data.db.entities.CratesEntity
import com.invictusbytes.gemaries.utils.AppExecutors
import javax.inject.Inject

class CratesRepository @Inject constructor(
    var cratesDao: CratesDao,
    var appExecutors: AppExecutors
) {


    fun addCrate(crate: CratesEntity) {

        appExecutors.diskIO().execute {
            this@CratesRepository.cratesDao.addCrate(crate)
        }
    }

    fun getCrateByCode(code: String): CratesEntity?{
        return cratesDao.getCrateByCode(code)
    }

    fun getAllCrates(): LiveData<List<CratesEntity>> {
        return cratesDao.getAllCrates()
    }

    fun getAssignedCrates(): LiveData<List<CratesEntity>> {
        return cratesDao.getAssignedCrates(true)
    }

    fun getUnAssignedCrates(): LiveData<List<CratesEntity>> {
        return cratesDao.getUnAssignedCrates(false)
    }
}