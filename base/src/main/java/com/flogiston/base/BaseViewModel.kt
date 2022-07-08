package com.flogiston.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<A>(
  protected val dispatcher: CoroutineDispatcher
) : ViewModel() {
  private val actionScope: CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
  protected val ioScope: CoroutineScope
    get() = CoroutineScope(viewModelScope.coroutineContext + dispatcher)

  val actions: Channel<A> = Channel()

  init {
    actions.receiveAsFlow()
      .onEach(::handleAction)
      .launchIn(actionScope)
  }

  open fun handleAction(action: A) {}

  override fun onCleared() {
    actionScope.cancel()
    super.onCleared()
  }
}