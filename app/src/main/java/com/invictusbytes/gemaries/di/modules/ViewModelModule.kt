package com.invictusbytes.gemaries.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.invictusbytes.gemaries.di.qualifires.ViewModelKey
import com.invictusbytes.gemaries.ui.clients.ClientsViewModel
import com.invictusbytes.gemaries.ui.crates.CratesViewModel
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
    abstract fun bindCCratesViewModel(cratesViewModel: CratesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GemariesViewModelFactory): ViewModelProvider.Factory
}