package com.profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import com.profile.presentation.models.Address
import com.profile.presentation.models.UserProfile
import com.profile.presentation.models.Vehicle
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProfileScreen(
    user: UserProfile,
    vehicles: PersistentList<Vehicle>,
    addresses: PersistentList<Address>,
    onEditProfile: () -> Unit,
    onManageBusiness: () -> Unit,
    onVehicleClick: (Vehicle) -> Unit,
    onAddVehicle: () -> Unit,
    onAddAddress: () -> Unit,
    onDeleteAddress: (Address) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp + innerPadding.calculateStartPadding(layoutDirection),
                top = 16.dp + innerPadding.calculateTopPadding(),
                end = 16.dp + innerPadding.calculateEndPadding(layoutDirection),
                bottom = 16.dp + innerPadding.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
            ProfileHeader(user, onEditProfile)
        }

        item {
            BusinessSection(onManageBusiness)
        }

        item {
            VehiclesSection(
                vehicles = vehicles,
                onVehicleClick = onVehicleClick,
                onAddVehicle = onAddVehicle
            )
        }

        item {
            AddressSection(
                addresses = addresses,
                onAddAddress = onAddAddress,
                onDeleteAddress = onDeleteAddress
            )
        }

        item {
            LogoutButton(onLogout)
        }
    }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val user = UserProfile(
        name = "Alexander Vance",
        email = "alexander.vance@precisionmotion.com",
        avatarUrl = "https://example.com/avatar.jpg"
    )

    val vehicles = persistentListOf(
        Vehicle("1", "Tesla Model S", "L717 PMN • Midnight Silver"),
        Vehicle("2", "Porsche Taycan", "P002 MOT • Jet Black"),
        Vehicle("3", "Audi e-tron GT", "A111 VEL • Daytona Gray"),
        Vehicle("4", "BMW i7", "M888 BAV • Mineral White"),
        Vehicle("5", "Lucid Air", "L005 AIR • Zenith Gold"),
        Vehicle("6", "Mercedes EQS", "E999 ELE • Obsidian Black"),
        Vehicle("7", "Rivian R1S", "R777 ADV • Forest Green"),
        Vehicle("8", "Ford F-150 Lightning", "F150 PWR • Antimatter Blue")
    )

    val addresses = persistentListOf(
        Address("1", "Home", "221B Baker St, London"),
        Address("2", "Office", "Canary Wharf, London"),
        Address("3", "Gym", "South Kensington, London"),
        Address("4", "Parent's House", "Notting Hill, London"),
        Address("5", "Vacation Home", "123 Beach Side, Brighton"),
        Address("6", "Coffee Shop", "456 Espresso Ave, Shoreditch")
    )

    ProfileScreen(
        user = user,
        vehicles = vehicles,
        addresses = addresses,
        onEditProfile = {},
        onManageBusiness = {},
        onVehicleClick = {},
        onAddVehicle = {},
        onAddAddress = {},
        onDeleteAddress = {},
        onLogout = {}
    )
}

@Composable
private fun ProfileHeader(
    user: UserProfile,
    onEditClick: () -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )

                FilledIconButton(
                    onClick = onEditClick,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(32.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null)
                }
            }

            Spacer(Modifier.height(12.dp))

            Text(
                user.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                user.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
@Preview
fun ProfileHeaderPreview() {
    ProfileHeader(
        user = UserProfile("John Doe", "john.mckinley@examplepetstore.com"),
        onEditClick = {}
    )
}

@Composable
private fun BusinessSection(
    onManageBusiness: () -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(Modifier.padding(16.dp)) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.Business,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Business Management",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.height(12.dp))

            ListItem(
                headlineContent = {
                    Text("Manage Business")
                },
                supportingContent = {
                    Text("View and edit your business profile")
                },
                trailingContent = {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                },
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                modifier = Modifier.clickable(onClick = onManageBusiness)
            )
        }
    }
}

@Composable
@Preview
fun BusinessSectionPreview() {
    BusinessSection(
        onManageBusiness = {}
    )
}

@Composable
private fun VehiclesSection(
    vehicles: PersistentList<Vehicle>,
    onVehicleClick: (Vehicle) -> Unit,
    onAddVehicle: () -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "My Vehicles",
                    style = MaterialTheme.typography.titleMedium
                )
                TextButton(onClick = onAddVehicle) {
                    Text("Add New")
                }
            }

            Spacer(Modifier.height(8.dp))

            vehicles.forEach { vehicle ->
                ListItem(
                    headlineContent = { Text(vehicle.name) },
                    supportingContent = { Text(vehicle.subtitle) },
                    leadingContent = {
                        Icon(Icons.Default.DirectionsCar, contentDescription = null)
                    },
                    trailingContent = {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    },
                    modifier = Modifier.clickable { onVehicleClick(vehicle) },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun VehiclesSectionPreview() {
    val vehicles = persistentListOf(
        Vehicle("1", "Tesla Model S", "L717 PMN • Midnight Silver"),
        Vehicle("2", "Porsche Taycan", "P002 MOT • Jet Black"),
        Vehicle("3", "Audi e-tron GT", "A111 VEL • Daytona Gray")
    )
    VehiclesSection(
        vehicles = vehicles,
        onVehicleClick = {},
        onAddVehicle = {}
    )
}
@Composable
fun AddressSection(
    addresses: PersistentList<Address>,
    onAddAddress: () -> Unit,
    onDeleteAddress: (Address) -> Unit
) {
    ElevatedCard(
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Saved Addresses",
                    style = MaterialTheme.typography.titleMedium
                )
                FilledIconButton(onClick = onAddAddress) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }

            Spacer(Modifier.height(8.dp))

            addresses.forEach { address ->
                ListItem(
                    headlineContent = { Text(address.label) },
                    supportingContent = { Text(address.address) },
                    trailingContent = {
                        IconButton(onClick = { onDeleteAddress(address) }) {
                            Icon(Icons.Default.Delete, contentDescription = null)
                        }
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun AddressSectionPreview() {
    AddressSection(
        addresses = persistentListOf(
            Address("1", "Home", "221B Baker St, London"),
            Address("2", "Office", "Canary Wharf, London")
        ),
        onAddAddress = {},
        onDeleteAddress = {}
    )
}

@Composable
fun LogoutButton(onLogout: () -> Unit) {
    FilledTonalButton(
        onClick = onLogout,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            "Logout",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
@Preview
fun LogoutButtonPreview() {
    LogoutButton( onLogout = {} )
}