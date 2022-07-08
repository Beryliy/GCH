package com.flogiston.auth_domain.repo

import com.flogiston.auth_domain.TokenProto
import com.flogiston.auth_domain.dto.AccessTokenResponse
import com.flogiston.auth_entity.Token

fun AccessTokenResponse.toEntity(): Token =
  Token(
    access_token,
    scope,
    token_type
  )

fun AccessTokenResponse.toProto(): TokenProto =
  TokenProto.newBuilder()
    .apply {
      accessToken = this@toProto.access_token
      scope = this@toProto.scope
      tokenType = this@toProto.token_type
    }.build()

fun toEntity(tokenProto: TokenProto): Token =
  tokenProto.run {
    Token(
      accessToken,
      scope,
      tokenType
    )
  }

