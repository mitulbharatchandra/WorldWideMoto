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
import com.profile.presentation.navigation.ProfileRoute
import com.profile.presentation.profile.ProfileScreenRoot
import com.business.presentation.navigation.BusinessRoute
import com.business.presentation.navigation.AddGarageRoute
import com.business.presentation.dashboard.BusinessDashboardScreenRoot
import com.business.presentation.add_garage.AddGarageScreenRoot
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
                    subclass(ProfileRoute::class, ProfileRoute.serializer())
                    subclass(BusinessRoute::class, BusinessRoute.serializer())
                    subclass(AddGarageRoute::class, AddGarageRoute.serializer())
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
                    },
                    onProfileClick = {
                        rootBackStack.add(ProfileRoute)
                    }
                )
            }
            entry<ProfileRoute> {
                ProfileScreenRoot(
                    onLogout = {
                        // Clear any potential back stack entries and go to auth
                        rootBackStack.remove(ProfileRoute)
                        rootBackStack.remove(HomeRoute)
                        rootBackStack.add(AuthRoute)
                    },
                    onManageBusiness = {
                        rootBackStack.add(BusinessRoute)
                    }
                )
            }
            entry<BusinessRoute> {
                BusinessDashboardScreenRoot(
                    onAddGarageClick = {
                        rootBackStack.add(AddGarageRoute)
                    }
                )
            }
            entry<AddGarageRoute> {
                AddGarageScreenRoot(
                    onNavigateBack = {
                        rootBackStack.remove(AddGarageRoute)
                    }
                )
            }
        }
    )
}