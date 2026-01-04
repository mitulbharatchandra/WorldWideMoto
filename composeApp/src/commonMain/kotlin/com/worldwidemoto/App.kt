package com.worldwidemoto
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kmp.designsystem.theme.AppTheme
import com.worldwidemoto.navigation.NavigationRoot
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        Scaffold { innerPadding ->
            NavigationRoot(
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
    }
}