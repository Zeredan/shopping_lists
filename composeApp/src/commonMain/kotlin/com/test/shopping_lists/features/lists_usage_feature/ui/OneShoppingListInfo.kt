package com.test.shopping_lists.features.lists_usage_feature.ui

import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.EaseOutCubic
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.EaseOutExpo
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.test.shopping_lists.core.ui.YesNoDialog
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ItemList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ShoppingList
import com.test.shopping_lists.features.lists_usage_feature.domain.ListUsageViewModel
import org.jetbrains.compose.resources.painterResource
import shopping_lists.composeapp.generated.resources.Res
import shopping_lists.composeapp.generated.resources.close
import shopping_lists.composeapp.generated.resources.compose_multiplatform
import shopping_lists.composeapp.generated.resources.expand
import shopping_lists.composeapp.generated.resources.fold
import kotlin.math.exp

@Composable
fun OneShoppingListInfo(
    modifier: Modifier = Modifier,
    vm: ListUsageViewModel,
    expanded: Boolean, //true - expanded, false - no expanded
    onExpandedChanged: (Boolean) -> Unit,
    shoppingList: ShoppingList,
    itemList: ItemList?,
) {
    Column(
        modifier = modifier
    )
    {
        var itemAddDialogExpanded by remember { mutableStateOf(false) }
        val itemNameText by vm.itemNameTextStateFlow.collectAsState()
        val itemNText by vm.itemNTextStateFlow.collectAsState()

        ItemAddDialog(
            expanded = itemAddDialogExpanded,
            itemName = itemNameText,
            itemN = itemNText,
            onExpandedChange = { itemAddDialogExpanded = it },
            onItemNameChanged = vm::updateItemName,
            onItemNChanged = vm::updateItemNText,
            onSubmit = {
                vm.submitAddElement(shoppingList.id)
                itemAddDialogExpanded = false
            }
        )

        var listDeletionDialogExpanded by remember { mutableStateOf(false) }
        val settingsUI by vm.settingsUI.collectAsState()
        YesNoDialog(
            title = "Удаление списка",
            expanded = listDeletionDialogExpanded,
            onExpandedChange = {
                listDeletionDialogExpanded = it
            },
            onYes = {
                vm.removeList(shoppingList.id)
                listDeletionDialogExpanded = false
            },
            onNo = {
                listDeletionDialogExpanded = false
            }
        )
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .linearGradient(listOf(Color(70, 10, 50), Color(200, 60, 150)))
                .height(60.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Row(
                modifier = Modifier
                    .weight(5f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                if (itemList != null) {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .clickable {
                                onExpandedChanged(!expanded)
                            }
                            .padding(3.dp)
                            .size(40.dp),
                        painter = painterResource(if (expanded) Res.drawable.fold else Res.drawable.expand),
                        contentDescription = null
                    )
                } else {
                    Text("loading")
                }
                Spacer(Modifier.width(20.dp))
                Text(shoppingList.name, color = Color(200, 200, 50))
                Spacer(Modifier.weight(1f))
                Spacer(Modifier.width(20.dp))
            }
            Text("создан: ${shoppingList.created}", modifier = Modifier.weight(4f), color = Color(240, 240, 240))
            Spacer(Modifier.width(20.dp))
            Image(
                modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .clickable {
                        if (settingsUI.askToDeleteList){
                            listDeletionDialogExpanded = true
                        }
                        else vm.removeList(shoppingList.id)
                    }
                    .padding(3.dp)
                    .size(40.dp),
                painter = painterResource(Res.drawable.close),
                contentDescription = null
            )
        }
        if (expanded && itemList != null){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp, 10.dp, 0.dp, 0.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            )
            {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            itemAddDialogExpanded = true
                        }
                        .linearGradient(listOf(Color(60, 140, 10), Color(20, 200, 20)))
                        .fillMaxWidth()
                        .padding(15.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text("+", fontSize = 26.sp, color = Color(200, 200, 0))
                }
                itemList.itemList.forEach {
                    var itemDeletionDialogExpanded by remember { mutableStateOf(false) }
                    YesNoDialog(
                        title = "Удаление предмета",
                        expanded = itemDeletionDialogExpanded,
                        onExpandedChange = { itemDeletionDialogExpanded = false },
                        onYes = {
                            vm.removeItem(shoppingList.id, it.id)
                        },
                        onNo = {
                        }
                    )
                    val linePercent by animateFloatAsState(
                        if (it.isCrossed) 1f else 0f,
                        animationSpec = tween(1000, easing = EaseOutExpo)
                    )
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        Row(
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .clickable {
                                    vm.crossOfItem(it.id)
                                }
                                .linearGradient(listOf(Color(20, 10, 150), Color(100, 60, 200)))
                                .padding(5.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            Text(it.name, color = Color(170, 170, 20))
                            Spacer(modifier = Modifier.weight(1f))
                            Image(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(5.dp))
                                    .clickable {
                                        if (settingsUI.askToDeleteItem) {
                                            itemDeletionDialogExpanded = true
                                        } else vm.removeItem(shoppingList.id, it.id)
                                    }
                                    .padding(3.dp)
                                    .size(40.dp),
                                painter = painterResource(Res.drawable.close),
                                contentDescription = null
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(linePercent)
                                .height(2.dp)
                                .background(Color.Black)
                                .align(Alignment.CenterStart)
                        )
                    }
                }
            }
        }
    }
}