package com.test.shopping_lists.features.lists_usage_feature.ui

import androidx.compose.foundation.background
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
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.shopping_lists.core.ui.linearGradient

@Composable
fun ListCreationDialog(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    listName: String,
    onExpandedChange: (Boolean) -> Unit,
    onListNameChanged: (String) -> Unit,
    onSubmit: () -> Unit
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
                    Text("Создание списка", fontSize = 26.sp)
                    TextField(
                        value = listName,
                        onValueChange = onListNameChanged,
                        label = {
                            Text("Название списка")
                        }
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .linearGradient(listOf(Color(0, 150, 170), Color(0, 100, 210)))
                            .clickable {
                                onSubmit()
                                onExpandedChange(false)
                            }
                            .fillMaxWidth(0.6f)
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text("Создать")
                    }
                }
            }
        )
    }
}

@Composable
fun ItemAddDialog(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    itemName: String,
    itemN: String,
    onExpandedChange: (Boolean) -> Unit,
    onItemNameChanged: (String) -> Unit,
    onItemNChanged: (String) -> Unit,
    onSubmit: () -> Unit
    ) {
    if (expanded) {
        AlertDialog(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
            onDismissRequest = { onExpandedChange(false) },
            confirmButton = {
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("Добавление элемента", fontSize = 26.sp)
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = onItemNameChanged,
                        label = {
                            Text("Название")
                        }
                    )
                    OutlinedTextField(
                        value = itemN,
                        onValueChange = onItemNChanged,
                        label = {
                            Text("Количество")
                        }
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(180, 0, 200))
                            .clickable(onClick = onSubmit)
                            .fillMaxWidth(0.6f)
                            .height(60.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text("Добавить")
                    }
                }
            }
        )
    }
}