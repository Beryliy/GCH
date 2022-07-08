package com.flogiston.auth_domain.repo

import com.flogiston.auth_domain.AuthClient
import com.flogiston.auth_domain.dto.AccessTokenRequest
import com.flogiston.auth_domain.storage.AuthDataStore
import com.flogiston.auth_entity.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepo(
  private val authClient: AuthClient,
  private val authDataStore: AuthDataStore
) {

  fun accessToken(): Flow<Token> = authDataStore.authToken.map(::toEntity)

  suspend fun obtainAccessToken(
    clientId: String,
    clientSecret: String,
    code: String
  ) {
    authClient.getAccessToken(clientId, clientSecret, code).toProto()
      .let { tokenProto -> authDataStore.persistAuthToken(tokenProto) }
  }

  /*suspend fun observeAccessToken(
    clientId: String,
    clientSecret: String,
    code: String,
    redirectUri: String = "https://flogiston.ghc.com"
  ): Flow<Token> =
    authClient.getAccessToken(clientId, clientSecret, code, redirectUri).toProto()
      .let { tokenProto ->
        authDataStore.persistAuthToken(tokenProto)
        authDataStore.authToken.map(::toEntity)
      }*/


}