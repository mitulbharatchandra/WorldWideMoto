package com.worldwidemoto.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.auth.presentation.navigation.AuthNavigation
import com.auth.presentation.navigation.AuthRoute
import com.home.presentation.navigation.HomeNavigation
import com.home.presentation.navigation.HomeRoute
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier,
    startDestination: NavKey
) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(AuthRoute::class, AuthRoute.serializer())
                    subclass(HomeRoute::class, HomeRoute.serializer())
                }
            }
        },
        startDestination
    )
    NavDisplay(
        modifier = modifier,
        backStack = rootBackStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<AuthRoute> {
                AuthNavigation(
                    onLoginSuccess = {
                        rootBackStack.remove(AuthRoute)
                        rootBackStack.add(HomeRoute)
                    }
                )
            }
            entry<HomeRoute> {
                HomeNavigation(
                    onSignOutSuccess = {
                        rootBackStack.remove(HomeRoute)
                        rootBackStack.add(AuthRoute)
                    }
                )
            }
        }
    )
}