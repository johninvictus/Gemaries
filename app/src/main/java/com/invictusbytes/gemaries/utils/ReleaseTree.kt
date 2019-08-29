package com.invictusbytes.gemaries.utils

import android.util.Log
import timber.log.Timber

class ReleaseTree: Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.DEBUG && priority == Log.VERBOSE)
            return

        // push to crashlytics
    }

}