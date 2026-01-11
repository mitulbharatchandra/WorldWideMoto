package com.auth.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
import com.core.presentation.util.navigation.ListDetailScene
import com.core.presentation.util.navigation.rememberListDetailSceneStrategy
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
    NavDisplay(
        backStack = authBackStack,
        sceneStrategy = rememberListDetailSceneStrategy(),
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        transitionSpec = {
            slideInHorizontally { it } + fadeIn() togetherWith
                    slideOutHorizontally { -it } + fadeOut()
        },
        popTransitionSpec = {
            slideInHorizontally { -it } + fadeIn() togetherWith
                    slideOutHorizontally { it } + fadeOut()
        },
        predictivePopTransitionSpec = {
            slideInHorizontally { -it } + fadeIn() togetherWith
                    slideOutHorizontally { it } + fadeOut()
        },
        entryProvider = entryProvider {
            entry<AuthRoute.Login>(
                metadata = ListDetailScene.listPane()
            ) {
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
            entry<AuthRoute.ForgotPassword>(
                metadata = ListDetailScene.detailPane()
            ) { key ->
                ForgetPasswordRoot(email = key.email)
            }
            entry<AuthRoute.SignInWithPhone>(
                metadata = ListDetailScene.detailPane()
            ) {
                SignInWithPhoneRoot()
            }
        }
    )
}