package com.flogiston.auth_domain

import com.flogiston.auth_domain.dto.AccessTokenResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthClient {

  @POST("/login/oauth/access_token")
  suspend fun getAccessToken(
    @Query("client_id") clientId: String,
    @Query("client_secret") clientSecret: String,
    @Query("code") code: String
  ): AccessTokenResponse
}