package com.flogiston.auth

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.flogiston.auth.AuthViewModel.Action
import com.flogiston.auth.di.AuthComponent
import com.flogiston.auth.login.Login
import com.flogiston.auth.oauth.OAuth
import com.flogiston.auth.oauth.OAuthViewModel
import com.flogiston.auth.oauth.OAuthViewModel.Action.CodeCallback
import com.flogiston.auth.oauth.di.OAuthComponent
import com.flogiston.auth.ui.theme.GHCTheme
import com.flogiston.base.BaseActivity

class AuthActivity : BaseActivity<Action, AuthViewModel>() {
  override val viewModelClass: Class<AuthViewModel> = AuthViewModel::class.java

  override fun injectDependencies() = AuthComponent.get().inject(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      GHCTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          val navController = rememberNavController()
          NavHost(
            navController = navController,
            startDestination = AuthRoute.Login.route,
          ) {
            composable(AuthRoute.Login.route) {
              Login(navController)
            }
            composable(AuthRoute.OAuth.route) {
              OAuthComponent.get(applicationContext).let { oauthComponent ->
                viewModel(
                  modelClass = OAuthViewModel::class.java,
                  factory = oauthComponent.viewModelFactory
                ).let { oAuthViewModel ->
                  OAuth(
                    navController,
                    oauthComponent.events,
                    { code -> oAuthViewModel.actions.trySend(CodeCallback(code)) },
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}