package org.example.bookstore

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.example.bookstore.screen.home.HomeScreen
import org.example.bookstore.screen.home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<HomeViewModel>()
    HomeScreen(
        viewModel = viewModel,
        modifier = Modifier.fillMaxSize()
    )
}