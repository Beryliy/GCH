package com.flogiston.auth_entity

data class Token(
  val accessToken: String,
  val scope: String,
  val tokenType: String
)