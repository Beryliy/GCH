package com.flogiston.auth.oauth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.flogiston.auth.oauth.OAuthViewModel
import com.flogiston.auth.oauth.OAuthViewModel.Event
import com.flogiston.auth_domain.repo.AuthRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel

class OAuthViewModelFactory(
  private val events: Channel<Event>,
  private val authRepo: AuthRepo,
  private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
    OAuthViewModel(events, authRepo, dispatcher) as T
}