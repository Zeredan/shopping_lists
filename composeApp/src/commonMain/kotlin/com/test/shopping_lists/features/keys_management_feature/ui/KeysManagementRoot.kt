package com.test.shopping_lists.features.keys_management_feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.shopping_lists.core.ui.YesNoDialog
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import com.test.shopping_lists.features.keys_management_feature.domain.KeysManagementViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import shopping_lists.composeapp.generated.resources.Res
import shopping_lists.composeapp.generated.resources.close
import shopping_lists.composeapp.generated.resources.compose_multiplatform

@OptIn(KoinExperimentalAPI::class)
@Composable
fun KeysManagementRoot(modifier: Modifier = Modifier) {
    val cs = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val vm = koinViewModel<KeysManagementViewModel>()

    val settings by vm.settingsUI.collectAsState()
    val keys by vm.keys.collectAsState(emptyList())
    val key by vm.selectedKey.collectAsState()

    var inputDialogExpanded by remember { mutableStateOf(false) }
    var generateDialogExpanded by remember { mutableStateOf(false) }

    val inpKey by vm.inputKeyStateFlow.collectAsState()
    val inpName by vm.inputNameStateFlow.collectAsState()
    CreateOrGenerateKeyDialog(
        inputDialogExpanded = inputDialogExpanded,
        generateDialogExpanded = generateDialogExpanded,
        key = inpKey,
        name = inpName,
        onChangeExpanded = {
            inputDialogExpanded = it.first
            generateDialogExpanded = it.second
        },
        onChangeKey = vm::updateInputKey,
        onChangeName = vm::updateInputName,
        onAddKey = {
            cs.launch {
                if (!vm.addKey(Key(inpKey, inpName)).await()) snackBarHostState.showSnackbar("Такого ключа не существует")
            }
        },
        onGenerateKey = {
            vm.generateKey(inpName)
        }
    )

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState
            )
        },
        backgroundColor = Color.Transparent
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Мои Ключи",
                fontSize = 36.sp,
                color = Color(200, 200, 0)
            )
            Divider(thickness = 2.dp)
            if (key == null) {
                Text(
                    "Выберите ключ или создайте кнопками снизу",
                    fontSize = 24.sp,
                    color = Color(180, 0, 20),
                    textAlign = TextAlign.Center
                )
            }
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(
                        width = 2.dp,
                        Brush.linearGradient(listOf(Color.Yellow,
                            Color(180, 170, 0))),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .linearGradient(listOf(Color.LightGray, Color.Gray))
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(keys) {
                    var deleteKeyDialogExpanded by remember { mutableStateOf(false) }
                    YesNoDialog(
                        title = "Удаление ключа",
                        expanded = deleteKeyDialogExpanded,
                        onExpandedChange = {deleteKeyDialogExpanded = it},
                        onYes = {
                            vm.removeKey(it)
                        },
                        onNo = {
                        }
                    )
                    Row(
                        modifier = Modifier
                            .padding(0.dp, 5.dp, 0.dp, 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .run {
                                if (it == key) {
                                    border(
                                        3.dp,
                                        Brush.linearGradient(listOf(Color.Green, Color.Cyan)),
                                        RoundedCornerShape(10.dp)
                                    )
                                } else this
                            }
                            .linearGradient(
                                if (it != key) {
                                    listOf(Color(70, 110, 50), Color(170, 120, 70))
                                } else {
                                    listOf(Color(140, 220, 0), Color(90, 255, 40))
                                }
                            )
                            .clickable {
                                vm.selectKey(it)
                            }
                            .padding(5.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(it.key, color = Color(10, 10, 250))
                        Spacer(Modifier.weight(1f))
                        Text(it.name, color = Color(10, 10, 250))
                        Spacer(Modifier.weight(1f))
                        Image(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    if (settings.askToDeleteKey){
                                        deleteKeyDialogExpanded = true
                                    }
                                    else vm.removeKey(it)
                                }
                                .padding(3.dp)
                                .size(40.dp),
                            painter = painterResource(Res.drawable.close),
                            contentDescription = null
                        )
                    }
                }
            }
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .linearGradient(listOf(Color(70, 0, 110), Color(170, 20, 180)))
                    .clickable {
                        generateDialogExpanded = true
                    }
                    .fillMaxWidth(0.7f)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Сгенерировать ключ",
                    color = Color(200, 200, 0)
                )
            }
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .linearGradient(listOf(Color(70, 0, 110), Color(170, 20, 180)))
                    .clickable {
                        inputDialogExpanded = true
                    }
                    .fillMaxWidth(0.7f)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Ввести ключ",
                    color = Color(200, 200, 0)
                )
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}