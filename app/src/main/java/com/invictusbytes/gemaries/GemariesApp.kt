package com.invictusbytes.gemaries

import com.invictusbytes.gemaries.di.DaggerAppComponent
import com.invictusbytes.gemaries.utils.ReleaseTree
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class GemariesApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}