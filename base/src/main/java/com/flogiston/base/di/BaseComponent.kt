package com.flogiston.base.di

import com.flogiston.base.di.qualifier.IO
import dagger.Component
import kotlinx.coroutines.CoroutineDispatcher

@Component(
  modules = [
    DispatchersModule::class
  ]
)
interface BaseComponent {
  @get:IO val ioDispatcher: CoroutineDispatcher

  @Component.Builder
  interface Builder {
    fun dispatchersModule(module: DispatchersModule): Builder
    fun build(): BaseComponent
  }

  companion object {
    fun get(): BaseComponent =
      DaggerBaseComponent.builder()
        .dispatchersModule(DispatchersModule())
        .build()
  }
}