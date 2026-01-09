package com.auth.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.auth.presentation.forgetpassword.ForgetPasswordRoot
import com.auth.presentation.login.LoginRoot
import com.auth.presentation.signinwithphone.SignInWithPhoneRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun AuthNavigation(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val authBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AuthRoute.Login::class, AuthRoute.Login.serializer())
                    subclass(AuthRoute.ForgotPassword::class, AuthRoute.ForgotPassword.serializer())
                    subclass(AuthRoute.SignInWithPhone::class, AuthRoute.SignInWithPhone.serializer())
                }
            }
        },
        AuthRoute.Login
    )
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        NavDisplay(
            backStack = authBackStack,
            modifier = Modifier.padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<AuthRoute.Login> {
                    LoginRoot(
                        onLoginSuccess = onLoginSuccess,
                        onForgotPasswordClick = {
                            authBackStack.add(
                                AuthRoute.ForgotPassword(email = it)
                            )
                        },
                        onSigInWithPhoneClick = {
                            authBackStack.add(AuthRoute.SignInWithPhone)
                        }
                    )
                }
                entry<AuthRoute.ForgotPassword> { key ->
                    ForgetPasswordRoot(email = key.email)
                }
                entry<AuthRoute.SignInWithPhone> {
                    SignInWithPhoneRoot()
                }
            }
        )
    }
}