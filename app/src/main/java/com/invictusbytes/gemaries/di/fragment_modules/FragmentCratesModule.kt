package com.invictusbytes.gemaries.di.fragment_modules

import com.invictusbytes.gemaries.ui.assigned.AssignedFragment
import com.invictusbytes.gemaries.ui.unassigned.UnassignedFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentCratesModule {

    @ContributesAndroidInjector
    abstract fun contributeAssignedFragment(): AssignedFragment

    @ContributesAndroidInjector
    abstract fun contributeUnassignedFragment(): UnassignedFragment
}