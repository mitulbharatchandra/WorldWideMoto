package com.worldwidemoto

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.apple.signin.model.AppleSignInResult
import com.apple.signin.model.AppleUser
import com.apple.signin.util.AppleRedirectHandler

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        intent.data?.let { uri ->
            if (uri.scheme == "worldwidemoto" && uri.host == "apple") {

                val idToken = uri.getQueryParameter("id_token")
                val code = uri.getQueryParameter("code")
                val rawNonce = AppleRedirectHandler.rawNonce

                if (idToken != null && code != null) {
                    AppleRedirectHandler.onResult?.invoke(
                        AppleSignInResult.Success(
                            appleUser = AppleUser(
                                idToken = idToken,
                                authorizationCode = code,
                                rawNonce = rawNonce,
                                email = null,
                                fullName = null
                            )
                        )
                    )
                } else {
                    AppleRedirectHandler.onResult?.invoke(
                        AppleSignInResult.Failure("Invalid Apple response")
                    )
                }

                AppleRedirectHandler.clear()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}