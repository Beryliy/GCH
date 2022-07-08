package com.flogiston.auth

import com.flogiston.auth.AuthViewModel.Action
import com.flogiston.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher

class AuthViewModel(
  dispatcher: CoroutineDispatcher
) : BaseViewModel<Action>(dispatcher) {

  override fun handleAction(action: Action) {
    when(action) {

    }
  }

  sealed interface Action {
    object Login : Action
  }
}