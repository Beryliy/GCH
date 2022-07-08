package com.flogiston.base.di

import com.flogiston.base.di.qualifier.IO
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DispatchersModule {

  @get:[Provides IO]
  val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
}