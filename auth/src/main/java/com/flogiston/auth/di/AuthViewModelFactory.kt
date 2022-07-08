package com.flogiston.auth.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.flogiston.auth.AuthViewModel
import kotlinx.coroutines.CoroutineDispatcher

class AuthViewModelFactory(
  private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
    AuthViewModel(dispatcher) as T
}