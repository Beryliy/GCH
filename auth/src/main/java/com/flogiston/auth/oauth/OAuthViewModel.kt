package com.flogiston.auth.oauth

import com.flogiston.auth.BuildConfig
import com.flogiston.auth.oauth.OAuthViewModel.Action
import com.flogiston.auth.oauth.OAuthViewModel.Action.CodeCallback
import com.flogiston.auth.oauth.OAuthViewModel.Event.ObtainTokenFailed
import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class OAuthViewModel(
  private val events: Channel<Event>,
  private val authRepo: AuthRepo,
  dispatcher: CoroutineDispatcher
) : BaseViewModel<Action>(dispatcher) {


  override fun handleAction(action: Action) {
    when (action) {
      is CodeCallback -> obtainAccessToken(action.code)
    }
  }

  private fun obtainAccessToken(code: String) {
    ioScope.launch (
      CoroutineExceptionHandler { coroutineContext, throwable ->
        events.trySend(ObtainTokenFailed(code))
      }
    ) {
      authRepo.obtainAccessToken(
        BuildConfig.GITHUB_CLIENT_ID,
        BuildConfig.GITHUB_CLIENT_SECRET,
        code
      )
    }
  }

  sealed interface Action {
    data class CodeCallback(val code: String) : Action
  }

  sealed interface Event {
    data class ObtainTokenFailed(val code: String) : Event

  }
}