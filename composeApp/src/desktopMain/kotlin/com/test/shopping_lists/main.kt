package com.test.shopping_lists

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.test.shopping_lists.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "shopping_lists",
    ) {
        App()
    }
}