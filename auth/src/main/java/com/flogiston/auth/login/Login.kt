package com.flogiston.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flogiston.auth.AuthRoute
import com.flogiston.auth.R.string
import com.flogiston.auth.ui.theme.GHCTheme

@Composable
fun Login(
  navController: NavController
) {
  Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
  ) {
    Button(
      onClick = {
        navController.navigate(AuthRoute.OAuth.route)
      }
    ) {
      Text(text = stringResource(id = string.login))
    }
  }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  GHCTheme {
    Login(
      rememberNavController()
    )
  }
}
