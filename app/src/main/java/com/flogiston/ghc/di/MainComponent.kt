package com.flogiston.ghc.di

import android.content.Context
import com.flogiston.auth_domain.di.AuthDomainComponent
import com.flogiston.base.di.BaseComponent
import com.flogiston.ghc.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
  dependencies = [
    AuthDomainComponent::class,
    BaseComponent::class
  ],
  modules = [
    MainModule::class
  ]
)
interface MainComponent {
  fun inject(activity: MainActivity)

  @Component.Builder
  interface Builder {
    @BindsInstance fun application(context: Context): Builder
    fun authDomainComponent(component: AuthDomainComponent): Builder
    fun baseComponent(component: BaseComponent): Builder
    fun mainModule(module: MainModule): Builder
    fun build(): MainComponent
  }

  companion object {
    fun get(context: Context): MainComponent =
      DaggerMainComponent.builder()
        .application(context)
        .authDomainComponent(AuthDomainComponent.get(context))
        .baseComponent(BaseComponent.get())
        .mainModule(MainModule())
        .build()
  }
}