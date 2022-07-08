package com.flogiston.auth.oauth

import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flogiston.auth.oauth.OAuthViewModel.Event
import com.flogiston.auth.ui.theme.GHCTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private const val CODE_URL_AUTHORITY = "flogiston.ghc.com"
private const val CODE_PARAM = "code"

@Composable
fun OAuth(
  navController: NavController,
  events: Flow<Event>,
  onCodeReceived: (code: String) -> Unit
) {
  AndroidView(factory = { context ->
    WebView(context).apply {
      layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
      )
      settings.javaScriptEnabled = true
      settings.domStorageEnabled = true
      webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
          view: WebView?,
          request: WebResourceRequest?
        ): Boolean =
          request?.run {
            if(url.authority == CODE_URL_AUTHORITY) {
              url.getQueryParameter(CODE_PARAM)?.let(onCodeReceived)
              true
            } else {
              false
            }

          } ?: false
      }
      loadUrl("https://github.com/login/oauth/authorize?scope=user:email&client_id=3602711a5fc0ea027077")
    }
  })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GHCTheme {
    OAuth(
      rememberNavController(),
      flowOf(),
      {}
    )
  }
}
