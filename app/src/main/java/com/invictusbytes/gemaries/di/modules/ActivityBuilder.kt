package com.invictusbytes.gemaries.di.modules

import com.invictusbytes.gemaries.di.fragment_modules.FragmentClientsModule
import com.invictusbytes.gemaries.di.fragment_modules.FragmentCratesModule
import com.invictusbytes.gemaries.ui.MainActivity
import com.invictusbytes.gemaries.ui.assign_crate.AssignCrateActivity
import com.invictusbytes.gemaries.ui.clients.ClientsActivity
import com.invictusbytes.gemaries.ui.crates.CratesActivity
import com.invictusbytes.gemaries.ui.scanner.ScannerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentCratesModule::class])
    internal abstract fun bindCratesActivity(): CratesActivity


    @ContributesAndroidInjector(modules = [FragmentClientsModule::class])
    internal abstract fun bindClientsActivity(): ClientsActivity

    @ContributesAndroidInjector
    internal abstract fun bindScannerActivity(): ScannerActivity

    @ContributesAndroidInjector
    internal abstract fun bindAssignCrateActivity(): AssignCrateActivity
}