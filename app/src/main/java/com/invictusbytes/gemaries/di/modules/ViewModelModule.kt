package com.invictusbytes.gemaries.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invictusbytes.gemaries.di.qualifires.ViewModelKey
import com.invictusbytes.gemaries.ui.assign_crate.AssignCrateViewModel
import com.invictusbytes.gemaries.ui.assigned.AssignedViewModel
import com.invictusbytes.gemaries.ui.assigned_clients.AssignedClientsViewModel
import com.invictusbytes.gemaries.ui.client_profile.ClientProfileViewModel
import com.invictusbytes.gemaries.ui.clients.ClientsViewModel
import com.invictusbytes.gemaries.ui.crates.CratesViewModel
import com.invictusbytes.gemaries.ui.scanner.ScannerViewModel
import com.invictusbytes.gemaries.ui.unassign_crate.UnassignViewModel
import com.invictusbytes.gemaries.ui.unassigned.UnassignedViewModel
import com.invictusbytes.gemaries.ui.unassigned_clients.UnAssignedClientsViewModel
import com.invictusbytes.gemaries.vo.GemariesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(ClientsViewModel::class)
    abstract fun bindClientsViewModel(clientsViewModel: ClientsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CratesViewModel::class)
    abstract fun bindCratesViewModel(cratesViewModel: CratesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AssignedViewModel::class)
    abstract fun bindAssignedViewModel(assignedViewModel: AssignedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnassignedViewModel::class)
    abstract fun bindUnassignedViewModel(unassignedViewModel: UnassignedViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ScannerViewModel::class)
    abstract fun bindScannerViewModel(scannerViewModel: ScannerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnAssignedClientsViewModel::class)
    abstract fun bindUnAssignedClientsViewModel(unAssignedClientsViewModel: UnAssignedClientsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(AssignedClientsViewModel::class)
    abstract fun bindAssignedClientsViewModel(assignedClientsViewModel: AssignedClientsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AssignCrateViewModel::class)
    abstract fun bindAssignCrateViewModel(assignCrateViewModel: AssignCrateViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ClientProfileViewModel::class)
    abstract fun bindClientProfileViewModel(clientProfileViewModel: ClientProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnassignViewModel::class)
    abstract fun bindUnassignViewModel(unassignViewModel: UnassignViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GemariesViewModelFactory): ViewModelProvider.Factory
}