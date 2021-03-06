package com.invictusbytes.gemaries.di.qualifires

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Suppress("unused")
@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)