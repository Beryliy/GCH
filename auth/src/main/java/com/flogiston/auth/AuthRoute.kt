package com.flogiston.auth

sealed class AuthRoute(val route: String) {
  object Login : AuthRoute("login")
  object OAuth : AuthRoute("oAuth")
}
