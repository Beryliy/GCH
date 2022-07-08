package com.flogiston.ghc.di

import androidx.lifecycle.ViewModelProvider
import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.base.di.qualifier.IO
import com.flogiston.ghc.MainViewModel.Event
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel

@Module
class MainModule {

  @get:Provides
  val eventsChannel: Channel<Event> = Channel()

  @Provides
  fun provideMainViewModelFactory(
    events: Channel<Event>,
    authRepo: AuthRepo,
    @IO dispatcher: CoroutineDispatcher
  ): ViewModelProvider.Factory = MainViewModelFactory(
    events,
    authRepo,
    dispatcher,
  )
}