package com.invictusbytes.gemaries.di.modules

import com.invictusbytes.gemaries.ui.MainActivity
import com.invictusbytes.gemaries.ui.crates.CratesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun bindCratesActivity(): CratesActivity
}