package com.invictusbytes.gemaries.di.fragment_modules

import com.invictusbytes.gemaries.ui.unassigned_clients.UnAssignedClientsFragment
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentClientsModule {

    @ContributesAndroidInjector
    abstract fun contributeAllClientsFragment(): UnAssignedClientsFragment


    @ContributesAndroidInjector
    abstract fun contributeAssignedClientsFragment(): AssignedClientsFragment
}