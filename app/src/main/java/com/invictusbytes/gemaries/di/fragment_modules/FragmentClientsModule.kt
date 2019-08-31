package com.invictusbytes.gemaries.di.fragment_modules

import com.invictusbytes.gemaries.ui.all_clients.AllClientsFragment
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentClientsModule {

    @ContributesAndroidInjector
    abstract fun contributeAllClientsFragment(): AllClientsFragment


    @ContributesAndroidInjector
    abstract fun contributeAssignedClientsFragment(): AssignedClientsFragment
}