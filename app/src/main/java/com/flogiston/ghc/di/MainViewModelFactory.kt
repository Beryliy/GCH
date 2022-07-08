package com.flogiston.ghc.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.ghc.MainViewModel
import com.flogiston.ghc.MainViewModel.Event
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel

class MainViewModelFactory(
  private val events: Channel<Event>,
  private val authRepo: AuthRepo,
  private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(
    events,
    authRepo,
    dispatcher
  ) as T
}