package com.flogiston.ghc

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.flogiston.auth.AuthActivity
import com.flogiston.base.BaseActivity
import com.flogiston.ghc.MainViewModel.Action
import com.flogiston.ghc.MainViewModel.Event
import com.flogiston.ghc.MainViewModel.Event.StartAuthorization
import com.flogiston.ghc.di.MainComponent
import com.flogiston.ghc.ui.theme.GHCTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MainActivity : BaseActivity<Action, MainViewModel>() {
  override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

  @Inject lateinit var events: Channel<Event>

  override fun injectDependencies() = MainComponent.get(applicationContext).inject(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    events.receiveAsFlow()
      .onEach(::handleEvent)
      .launchIn(lifecycleScope)
    setContent {
      GHCTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Greeting("Android")
        }
      }
    }
  }

  private fun handleEvent(event: Event) {
    when (event) {
      StartAuthorization -> startActivity(Intent(this, AuthActivity::class.java))
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GHCTheme {
    Greeting("Android")
  }
}