package com.home.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Garage
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.core.presentation.util.navigation.BottomNavItem
import com.core.presentation.util.navigation.BottomNavigationAppBar
import com.core.presentation.util.navigation.Navigator
import com.core.presentation.util.navigation.rememberNavigationState
import com.core.presentation.util.navigation.toEntries
import com.home.presentation.home.HomeScreenRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun HomeNavigation(
    onSignOutSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationState = rememberNavigationState(
        startRoute = HomeRoute.Garages,
        topLevelRoutes = HOME_DESTINATIONS.keys,
        serializersConfig = serializersConfig
    )
    val navigator = remember {
        Navigator(navigationState)
    }
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationAppBar(
                selectedKey = navigationState.topLevelRoute,
                onSelectKey = {
                    navigator.navigate(it)
                },
                destinations = HOME_DESTINATIONS
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<HomeRoute.Garages> {
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("Garages")
                            Button(
                                onClick = {
                                    navigator.navigate(HomeRoute.GarageDetail)
                                }
                            ) {
                                Text("Garage Detail")
                            }
                        }
                    }
                    entry<HomeRoute.Map> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Map")
                        }
                    }
                    entry<HomeRoute.History> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            HomeScreenRoot(
                                onSignOutSuccess = {
                                    onSignOutSuccess()
                                }
                            )
                        }
                    }
                    entry<HomeRoute.GarageDetail> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Garage Detail")
                        }
                    }
                }
            )
        )
    }
}

val HOME_DESTINATIONS = mapOf(
    HomeRoute.Garages to BottomNavItem(
        icon = Icons.Outlined.Garage,
        title = "Garages"
    ),
    HomeRoute.Map to BottomNavItem(
        icon = Icons.Outlined.Map,
        title = "Map"
    ),
    HomeRoute.History to BottomNavItem(
        icon = Icons.Outlined.History,
        title = "History"
    ),
)

val serializersConfig = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeRoute.Garages::class, HomeRoute.Garages.serializer())
            subclass(HomeRoute.Map::class, HomeRoute.Map.serializer())
            subclass(HomeRoute.History::class, HomeRoute.History.serializer())
            subclass(HomeRoute.GarageDetail::class, HomeRoute.GarageDetail.serializer())
        }
    }
}