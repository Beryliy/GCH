package com.flogiston.auth_domain.di

import android.content.Context
import com.flogiston.auth_domain.AuthClient
import com.flogiston.auth_domain.repo.AuthRepo
import com.flogiston.auth_domain.storage.AuthDataStore
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import okhttp3.logging.HttpLoggingInterceptor.Logger
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AuthDomainModule {

  @Provides
  fun provideAuthDataStore(context: Context): AuthDataStore = AuthDataStore(context)

  @Provides
  fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(
      HttpLoggingInterceptor().apply {
        setLevel(Level.BODY)
      }
    )
    .addInterceptor { chain ->
      chain.request().newBuilder()
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .build()
        .let(chain::proceed)
    }
    .build()

  @get:Provides
  val moshi: Moshi = Moshi.Builder().build()

  @Provides
  fun provideRetrofit(
    okHttpClient: OkHttpClient,
    moshi: Moshi
  ): Retrofit =
    Retrofit.Builder()
      .baseUrl("https://github.com/")
      .client(okHttpClient)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .build()

  @Provides
  fun provideAuthClient(retrofit: Retrofit): AuthClient = retrofit.create(AuthClient::class.java)

  @Provides
  fun provideAuthRepo(
    authClient: AuthClient,
    authDataStore: AuthDataStore
  ): AuthRepo = AuthRepo(authClient, authDataStore)
}