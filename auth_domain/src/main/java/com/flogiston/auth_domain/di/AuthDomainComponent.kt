package com.flogiston.auth_domain.di

import android.content.Context
import com.flogiston.auth_domain.repo.AuthRepo
import dagger.BindsInstance
import dagger.Component

@Component(
  modules = [
    AuthDomainModule::class
  ]
)
interface AuthDomainComponent {
  fun authRepo(): AuthRepo

  @Component.Builder
  interface Builder {
    @BindsInstance fun application(context: Context): Builder
    fun authDomainModule(module: AuthDomainModule): Builder
    fun build(): AuthDomainComponent
  }

  companion object {
    fun get(context: Context): AuthDomainComponent =
      DaggerAuthDomainComponent.builder()
        .application(context)
        .authDomainModule(AuthDomainModule())
        .build()
  }
}