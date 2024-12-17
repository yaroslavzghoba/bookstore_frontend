package org.example.bookstore

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.bookstore.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "Bookstore",
    ) {
        App()
    }
}