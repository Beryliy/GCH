package com.flogiston.ghc

import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.base.BaseViewModel
import com.flogiston.ghc.MainViewModel.Action
import com.flogiston.ghc.MainViewModel.Event.StartAuthorization
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
  private val events: Channel<Event>,
  private val authRepo: AuthRepo,
  dispatcher: CoroutineDispatcher
) : BaseViewModel<Action>(dispatcher) {

  init {
    checkAuthorizationState()
  }

  private fun checkAuthorizationState() {
      authRepo.accessToken()
        .onEach { accessToken ->
          if (accessToken.accessToken.isBlank()) {
            events.trySend(StartAuthorization)
          }
        }.launchIn(ioScope)
  }

  sealed interface Action {

  }

  sealed interface Event {
    object StartAuthorization : Event
  }
}