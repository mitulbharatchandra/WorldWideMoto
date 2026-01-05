package com.worldwidemoto
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.auth.presentation.navigation.AuthRoute
import com.home.presentation.navigation.HomeRoute
import com.kmp.designsystem.theme.AppTheme
import com.worldwidemoto.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    viewModel: AppViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    AppTheme {
        Scaffold { innerPadding ->

            if(state.isLoading) {
                CircularProgressIndicator()
            } else {
                NavigationRoot(
                    modifier = Modifier
                        .padding(innerPadding),
                    startDestination = if (state.isLoggedIn) HomeRoute else AuthRoute
                )
            }
        }
    }
}