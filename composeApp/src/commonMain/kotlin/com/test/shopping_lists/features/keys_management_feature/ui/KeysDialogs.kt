package com.test.shopping_lists.features.keys_management_feature.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key

@Composable
fun CreateOrGenerateKeyDialog(
    modifier: Modifier = Modifier,
    inputDialogExpanded: Boolean,
    generateDialogExpanded: Boolean,
    key: String,
    name: String,
    onChangeExpanded: (Pair<Boolean, Boolean>) -> Unit,
    onChangeKey: (String) -> Unit,
    onChangeName: (String) -> Unit,
    onAddKey: () -> Unit,
    onGenerateKey: () -> Unit
) {
    if (inputDialogExpanded || generateDialogExpanded){
        AlertDialog(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth(),
            onDismissRequest = {
                onChangeExpanded(false to false)
            },
            confirmButton = {},
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(if (generateDialogExpanded) "Генерация ключа" else "Добавление ключа", fontSize = 26.sp)
                    Spacer(Modifier.height(20.dp))
                    if (inputDialogExpanded){
                        TextField(
                            value = key,
                            onValueChange = onChangeKey,
                            label = {
                                Text("Ключ")
                            }
                        )
                    }
                    TextField(
                        value = name,
                        onValueChange = onChangeName,
                        label = {
                            Text("Название")
                        }
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .linearGradient(listOf(Color(0, 150, 170), Color(0, 100, 210)))
                            .clickable {
                                when{
                                    inputDialogExpanded -> onAddKey()
                                    generateDialogExpanded -> onGenerateKey()
                                }
                                onChangeExpanded(false to false)
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