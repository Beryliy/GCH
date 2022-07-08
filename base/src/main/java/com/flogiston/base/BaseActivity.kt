package com.flogiston.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

abstract class BaseActivity<A, VM: BaseViewModel<A>> : ComponentActivity() {
  abstract val viewModelClass: Class<VM>

  private lateinit var viewModel: VM

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    injectDependencies()
    viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass]
  }

  abstract fun injectDependencies()

}