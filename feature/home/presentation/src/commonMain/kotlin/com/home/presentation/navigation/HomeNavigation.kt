package com.home.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Garage
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import androidx.savedstate.serialization.SavedStateConfiguration
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import com.core.presentation.util.navigation.BottomNavItem
import com.core.presentation.util.navigation.BottomNavigationAppBar
import com.core.presentation.util.navigation.ListDetailScene
import com.core.presentation.util.navigation.Navigator
import com.core.presentation.util.navigation.rememberListDetailSceneStrategy
import com.core.presentation.util.navigation.rememberNavigationState
import com.core.presentation.util.navigation.toEntries
import com.home.presentation.garage_details.GarageDetailsScreen
import com.home.presentation.garage_list.DiscoverScreen
import com.home.presentation.garage_list.GarageListProvider
import com.home.presentation.home.HomeScreenRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.ui.tooling.preview.Preview

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
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    // Seed initial detail ONLY ONCE and ONLY for wide screens
    LaunchedEffect(windowSizeClass) {
        val isWide =
            windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)

        val hasDetail = navigator.state.backStacks.keys.any { it is HomeRoute.GarageDetail }

        if (isWide && !hasDetail) {
            navigator.navigate(HomeRoute.GarageDetail(GarageListProvider.garageList[0].id))
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
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
            sceneStrategy = rememberListDetailSceneStrategy(),
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
            entries = navigationState.toEntries(
                entryProvider {
                    entry<HomeRoute.Garages>(
                        metadata = ListDetailScene.listPane()
                    ) {
                        DiscoverScreen(
                            modifier = Modifier
                                .fillMaxSize(),
                            title = "Discover Garages",
                            searchHint = "Search by Garage, Service or Location",
                            services = GarageListProvider.garageList,
                            onGarageClick = {
                                navigator.navigate(HomeRoute.GarageDetail(it))
                            },
                            isListSelected = true,
                            onSearchChange = {},
                            onViewToggle = {}
                        )
                    }
                    entry<HomeRoute.Map>(
                        metadata = ListDetailScene.listPane()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Map")
                        }
                    }
                    entry<HomeRoute.History>(
                        metadata = ListDetailScene.listPane()
                    ) {
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
                    entry<HomeRoute.GarageDetail>(
                        metadata = ListDetailScene.detailPane()
                    ) {
                        GarageDetailsScreen(
                            garageId = it.garageId,
                            onBookServiceClick = {}
                        )
                    }
                }
            )
        )
    }
}

@Composable
@Preview
fun HomeNavigationPreview() {
    PreviewNavWrapper {
        HomeNavigation(
            onSignOutSuccess = {}
        )
    }
}

@Composable
private fun PreviewNavWrapper(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalNavigationEventDispatcherOwner provides
                FakeNavigationEventDispatcherOwner()
    ) {
        content()
    }
}

class FakeNavigationEventDispatcherOwner :
    NavigationEventDispatcherOwner {

    override val navigationEventDispatcher =
        NavigationEventDispatcher()
}


val HOME_DESTINATIONS: Map<NavKey, BottomNavItem> = mapOf(
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