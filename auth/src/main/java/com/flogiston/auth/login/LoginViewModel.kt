package com.flogiston.auth.login

import com.flogiston.auth.AuthViewModel.Action
import com.flogiston.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

open class LoginViewModel(
  dispatcher: CoroutineDispatcher
) : BaseViewModel<Action>(dispatcher) {

  sealed interface Action {

  }
}

class TestLoginViewModel : LoginViewModel(Dispatchers.IO)

