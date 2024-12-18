package com.test.shopping_lists.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun YesNoDialog(
    modifier: Modifier = Modifier,
    title: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onYes: () -> Unit,
    onNo: () -> Unit,
) {
    if (expanded){
        AlertDialog(
            modifier = modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            onDismissRequest = {
                onExpandedChange(false)
            },
            confirmButton = {},
            text = {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(title, fontSize = 26.sp)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    )
                    {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .linearGradient(listOf(Color(170, 150, 0), Color(210, 100, 0)))
                                .clickable {
                                    onYes()
                                    onExpandedChange(false)
                                }
                                .width(100.dp)
                                .height(60.dp),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text("Удалить")
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .linearGradient(listOf(Color(0, 150, 170), Color(0, 100, 210)))
                                .clickable {
                                    onNo()
                                    onExpandedChange(false)
                                }
                                .width(100.dp)
                                .height(60.dp),
                            contentAlignment = Alignment.Center
                        )
                        {
                            Text("Отмена")
                        }
                    }
                }
            }
        )
    }
}