package com.invictusbytes.gemaries.di

import android.app.Application
import com.invictusbytes.gemaries.GemariesApp
import com.invictusbytes.gemaries.di.modules.ActivityBuilder
import com.invictusbytes.gemaries.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Suppress("unused")
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<GemariesApp> {

    fun inject(app: Application)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: GemariesApp)

}