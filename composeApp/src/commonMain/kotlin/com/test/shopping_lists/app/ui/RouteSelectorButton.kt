package com.test.shopping_lists.app.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RouteSelectorButton(
    modifier: Modifier = Modifier,
    color: Color,
    imageResource: DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit
) : Boolean {
    Image(
        modifier = modifier
            .border(
                width = if (isSelected) 3.dp else 2.dp,
                brush = Brush.linearGradient(
                    listOf(
                        color,
                        color.copy(red = color.blue, green = color.red, blue = color.green)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .fillMaxHeight()
            .aspectRatio(1.5f),
        painter = painterResource(imageResource),
        contentDescription = null
    )
    return true
}