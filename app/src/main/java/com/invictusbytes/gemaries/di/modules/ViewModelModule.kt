package com.invictusbytes.gemaries.di.modules

import androidx.lifecycle.ViewModelProvider
import com.invictusbytes.gemaries.vo.GemariesViewModelFactory
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: GemariesViewModelFactory): ViewModelProvider.Factory
}