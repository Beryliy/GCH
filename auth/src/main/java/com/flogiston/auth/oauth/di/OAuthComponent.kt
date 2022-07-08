package com.flogiston.auth.oauth.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.flogiston.auth.oauth.OAuthViewModel.Event
import com.flogiston.auth_domain.di.AuthDomainComponent
import com.flogiston.base.di.BaseComponent
import dagger.Component
import kotlinx.coroutines.flow.Flow

@Component(
  dependencies = [
    AuthDomainComponent::class,
    BaseComponent::class,
  ],
  modules = [
    OAuthModule::class
  ]
)
interface OAuthComponent {
  val viewModelFactory: ViewModelProvider.Factory
  val events: Flow<Event>

  @Component.Builder
  interface Builder {
    fun authDomainComponent(component: AuthDomainComponent): Builder
    fun baseComponent(component: BaseComponent): Builder
    fun oAuthModule(module: OAuthModule): Builder
    fun build(): OAuthComponent
  }

  companion object {
    fun get(context: Context): OAuthComponent =
      DaggerOAuthComponent.builder()
        .authDomainComponent(AuthDomainComponent.get(context))
        .baseComponent(BaseComponent.get())
        .oAuthModule(OAuthModule())
        .build()
  }
}