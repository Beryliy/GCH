package com.flogiston.auth.oauth.di

import androidx.lifecycle.ViewModelProvider
import com.flogiston.auth.oauth.OAuthViewModel.Event
import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.base.di.qualifier.IO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

@Module
class OAuthModule {

  private val _events: Channel<Event> = Channel()

  @get:Provides val events: Flow<Event> = _events.receiveAsFlow()

  @Provides
  fun provideViewModelFactory(
    authRepo: AuthRepo,
    @IO dispatcher: CoroutineDispatcher
  ): ViewModelProvider.Factory =
    OAuthViewModelFactory(_events, authRepo, dispatcher)
}