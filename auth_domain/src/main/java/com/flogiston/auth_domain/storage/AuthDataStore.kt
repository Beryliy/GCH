package com.flogiston.auth_domain.storage

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.flogiston.auth_domain.TokenProto

import com.flogiston.auth_entity.Token
import com.google.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.flow.Flow
import java.io.InputStream
import java.io.OutputStream

val Context.tokenDataStore: DataStore<TokenProto> by dataStore(
  fileName = "settings.pb",
  serializer = TokenSerializer
)

class AuthDataStore(private val context: Context) {
  val authToken: Flow<TokenProto> = context.tokenDataStore.data

  suspend fun persistAuthToken(token: TokenProto) {
    context.tokenDataStore.updateData { oldToken -> token }
  }
}

object TokenSerializer : Serializer<TokenProto> {
  override val defaultValue: TokenProto = TokenProto.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): TokenProto =
    try {
      TokenProto.parseFrom(input)
    } catch (ipbe: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read proto.", ipbe)
    }

  override suspend fun writeTo(t: TokenProto, output: OutputStream) {
    t.writeTo(output)
  }
}