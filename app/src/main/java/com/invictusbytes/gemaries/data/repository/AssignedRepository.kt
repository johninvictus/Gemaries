package com.invictusbytes.gemaries.data.repository

import com.invictusbytes.gemaries.data.db.dao.AssignedDao
import com.invictusbytes.gemaries.data.db.entities.Assigned
import com.invictusbytes.gemaries.utils.AppExecutors
import javax.inject.Inject

class AssignedRepository @Inject constructor(
    var appExecutors: AppExecutors,
    var assignedDao: AssignedDao
) {
    fun addAssigned(assigned: Assigned) {
        appExecutors.diskIO().execute {
            assignedDao.addEntry(assigned)
        }
    }
}