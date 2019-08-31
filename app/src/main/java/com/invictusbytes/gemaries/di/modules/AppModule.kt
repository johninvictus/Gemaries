package com.invictusbytes.gemaries.di.modules

import dagger.Module


@Module(
    includes = [
        ViewModelModule::class,
        RoomModule::class
    ]
)
class AppModule