package com.test.shopping_lists.features.settings_feature.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Slider
import androidx.compose.material.Switch
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.shopping_lists.core.ui.YesNoDialog
import com.test.shopping_lists.core.ui.linearGradient
import com.test.shopping_lists.features.settings_feature.domain.SettingsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import shopping_lists.composeapp.generated.resources.Res
import shopping_lists.composeapp.generated.resources.compose_multiplatform

@Composable
fun SettingsRoot(modifier: Modifier = Modifier) {
    val vm = koinViewModel<SettingsViewModel>()

    val settings by vm.settingsUI.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Настройки",
            fontSize = 36.sp,
            color = Color(200, 200, 0)
        )
        Divider(thickness = 2.dp)
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .weight(1f)
        ) {
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text("Спрашивать при удалении списка")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = settings.askToDeleteList,
                        onCheckedChange = { vm.updateSettingsUI(settings.copy(askToDeleteList = it)) }
                    )
                }
            }
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text("Спрашивать при удалении предмета")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = settings.askToDeleteItem,
                        onCheckedChange = { vm.updateSettingsUI(settings.copy(askToDeleteItem = it)) }
                    )
                }
            }
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text("Спрашивать при удалении ключа")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = settings.askToDeleteKey,
                        onCheckedChange = { vm.updateSettingsUI(settings.copy(askToDeleteKey = it)) }
                    )
                }
            }
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Text("Автооткрытие списков")
                    Spacer(Modifier.weight(1f))
                    Switch(
                        checked = settings.autoExpandLists,
                        onCheckedChange = { vm.updateSettingsUI(settings.copy(autoExpandLists = it)) }
                    )
                }
            }
        }
    }
}