package com.apple.signin

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.apple.signin.model.AppleSignInResult
import com.apple.signin.util.AppleRedirectHandler
import com.apple.signin.util.findActivity
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.CodingErrorAction
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom

@Composable
actual fun AppleSignInButton(
    onResult: (AppleSignInResult) -> Unit,
    content: @Composable ((onClick: () -> Unit) -> Unit)
) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }

    val onClick = remember {
        {
            if (activity == null) {
                onResult(
                    AppleSignInResult.Failure("Activity not found")
                )
                return@remember
            }

            val rawNonce = generateNonce(32)
            val hashedNonce = sha256(rawNonce)

            AppleRedirectHandler.rawNonce = rawNonce
            AppleRedirectHandler.onResult = onResult

            val authUrl =
                "https://appleid.apple.com/auth/authorize" +
                        "?client_id=YOUR_SERVICE_ID" +
                        "&redirect_uri=worldwidemoto://apple/callback" +
                        "&response_type=code id_token" +
                        "&scope=name email" +
                        "&nonce=$hashedNonce" +
                        "&response_mode=fragment"

            val intent = Intent(
                Intent.ACTION_VIEW,
                authUrl.toUri()
            )

            activity.startActivity(intent)
        }
    }

    content(onClick)
}

private fun generateNonce(length: Int): String {
    val generator = SecureRandom()

    val charsetDecoder = StandardCharsets.US_ASCII.newDecoder()
    charsetDecoder.onUnmappableCharacter(CodingErrorAction.IGNORE)
    charsetDecoder.onMalformedInput(CodingErrorAction.IGNORE)

    val bytes = ByteArray(length)
    val inBuffer = ByteBuffer.wrap(bytes)
    val outBuffer = CharBuffer.allocate(length)
    while (outBuffer.hasRemaining()) {
        generator.nextBytes(bytes)
        inBuffer.rewind()
        charsetDecoder.reset()
        charsetDecoder.decode(inBuffer, outBuffer, false)
    }
    outBuffer.flip()
    return outBuffer.toString()
}

private fun sha256(s: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(s.toByteArray())
    val hash = StringBuilder()
    for (c in digest) {
        hash.append(String.format("%02x", c))
    }
    return hash.toString()
}