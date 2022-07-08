package com.flogiston.auth_domain.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccessTokenRequest(
  val clientId: String,
  val clientSecret: String,
  val code: String,
  val redirectUri: String
)

@JsonClass(generateAdapter = true)
data class AccessTokenResponse(
  val access_token: String,
  val scope: String,
  val token_type: String
)