package com.flogiston.auth.di

import androidx.lifecycle.ViewModelProvider
import com.flogiston.base.di.qualifier.IO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher

@Module
class AuthModule {

  @Provides
  fun provideViewModelFactory(
    @IO dispatcher: CoroutineDispatcher
  ): ViewModelProvider.Factory =
    AuthViewModelFactory(dispatcher)
}