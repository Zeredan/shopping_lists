package com.test.shopping_lists.app

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.shopping_lists.app.ui.RouteSelectorButton
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.features.keys_management_feature.ui.KeysManagementRoot
import com.test.shopping_lists.features.lists_usage_feature.ui.ListUsageRoot
import com.test.shopping_lists.features.settings_feature.ui.SettingsRoot
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import shopping_lists.composeapp.generated.resources.Res
import shopping_lists.composeapp.generated.resources.compose_multiplatform
import shopping_lists.composeapp.generated.resources.keys
import shopping_lists.composeapp.generated.resources.keys_management
import shopping_lists.composeapp.generated.resources.lists_usage
import shopping_lists.composeapp.generated.resources.options
import shopping_lists.composeapp.generated.resources.settings
import shopping_lists.composeapp.generated.resources.shopping_list


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val listUsage = stringResource(Res.string.lists_usage)
        val keysManagement = stringResource(Res.string.keys_management)
        val settings = stringResource(Res.string.settings)

        Column(
            modifier = Modifier
                .linearGradient(listOf(Color.Gray, Color.DarkGray))
                .fillMaxSize()
        )
        {
            NavHost(
                modifier = Modifier
                    .weight(90f)
                    .fillMaxWidth(),
                navController = navController,
                startDestination = listUsage
            )
            {
                composable(listUsage) {
                    ListUsageRoot(
                        onRequireKey = {
                            navController.navigate(keysManagement)
                        }
                    )
                }
                composable(keysManagement) {
                    KeysManagementRoot()
                }
                composable(settings) {
                    SettingsRoot()
                }
            }
            Divider(thickness = 2.dp, startIndent = 10.dp)
            Row(
                modifier = Modifier
                    .background(Color.Black)
                    .padding(10.dp)
                    .weight(10f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                val color by
                    rememberInfiniteTransition("color").animateColor(
                        label = "color1",
                        initialValue = Color.Green,
                        targetValue = Color.Yellow,
                        animationSpec = infiniteRepeatable(
                            tween(1000, easing = LinearEasing),
                            RepeatMode.Reverse
                        )
                    )
                data class SelectOption(
                    val img: DrawableResource,
                    val name: String
                )
                listOf(
                    SelectOption(
                        img = Res.drawable.shopping_list,
                        name = listUsage
                    ),
                    SelectOption(
                        img = Res.drawable.keys,
                        name = keysManagement
                    ),
                    SelectOption(
                        img = Res.drawable.options,
                        name = settings
                    )
                ).forEach {
                    val isSelected = navController.currentDestination?.route == it.name
                    RouteSelectorButton(
                        imageResource = it.img,
                        color = if (isSelected) color else Color.White,
                        isSelected = isSelected,
                        onClick = {
                            if (navController.currentDestination?.route != it.name) {
                                navController.navigate(it.name) {
                                    popUpTo(listUsage) { inclusive = it.name == listUsage }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}