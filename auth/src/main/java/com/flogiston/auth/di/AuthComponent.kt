package com.flogiston.auth.di

import com.flogiston.auth.AuthActivity
import com.flogiston.base.di.BaseComponent
import dagger.Component

@Component(
  dependencies = [
    BaseComponent::class
  ],
  modules = [
    AuthModule::class
  ]
)
interface AuthComponent {
  fun inject(activity: AuthActivity)

  @Component.Builder
  interface Builder {
    fun baseComponent(component: BaseComponent): Builder
    fun authModule(module: AuthModule): Builder
    fun build(): AuthComponent
  }

  companion object {
    fun get(): AuthComponent =
      DaggerAuthComponent.builder()
        .baseComponent(BaseComponent.get())
        .authModule(AuthModule())
        .build()
  }
}