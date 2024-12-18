@file:OptIn(KoinExperimentalAPI::class)

package com.test.shopping_lists.features.lists_usage_feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.datas.shopping_saved_data.MODEL.Key
import com.test.shopping_lists.features.lists_usage_feature.domain.ListUsageViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
fun ListUsageRoot(
    modifier: Modifier = Modifier,
    onRequireKey: () -> Unit
) {
    println("aaapp")
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val vm = koinViewModel<ListUsageViewModel>()

        val settingsUI by vm.settingsUI.collectAsState()
        val listName by vm.listNameTextStateFlow.collectAsState()
        var listCreationDialogExpanded by remember { mutableStateOf(false) }
        ListCreationDialog(
            expanded = listCreationDialogExpanded,
            listName = listName,
            onExpandedChange = {listCreationDialogExpanded = it},
            onListNameChanged = vm::updateListName,
            onSubmit = {
                vm.createList()
                listCreationDialogExpanded = false
                //vm.updateListName("")
            }
        )
        Text(
            "Мои списки покупок",
            fontSize = 36.sp,
            color = Color(200, 200, 0)
        )
        Divider(thickness = 2.dp)
        val key by vm.selectedKey.collectAsState()
        if (key == null) {
            Text("Сейчас не выбран ключ", fontSize = 26.sp, color = Color(190, 50, 0))
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .linearGradient(listOf(Color(0, 150, 170), Color(0, 100, 210)))
                    .clickable {
                        onRequireKey()
                    }
                    .fillMaxWidth(0.6f)
                    .height(100.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("Выбрать", fontSize = 26.sp, color = Color(170, 170, 0))
            }
        }
        else {
            Text(
                "Используется ключ: ${key!!.key}\n(${key!!.name})",
                fontSize = 26.sp,
                color = Color(10, 190, 60),
                modifier = Modifier.padding(10.dp),
                textAlign = TextAlign.Center
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .clickable {
                        listCreationDialogExpanded = true
                    }
                    .linearGradient(listOf(Color(160, 160, 250), Color(0, 140, 140)))
                    .fillMaxWidth(0.6f)
                    .height(60.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("Добавить список", color = Color(255, 255, 200))
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
            )
            {
                items(vm.fullInfoShoppingLists.toList()) { (k, v) ->
                    OneShoppingListInfo(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 5.dp, 0.dp, 5.dp),
                        vm = vm,
                        expanded = vm.expandedListsIds.contains(k.id),
                        onExpandedChanged = {
                            vm.updateExpanded(k.id, it)
                        },
                        shoppingList = k,
                        itemList = v,
                    )
                }
            }
        }
    }
}