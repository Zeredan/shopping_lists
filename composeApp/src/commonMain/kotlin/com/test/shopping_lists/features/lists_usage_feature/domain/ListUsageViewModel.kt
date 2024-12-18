package com.test.shopping_lists.features.lists_usage_feature.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.shopping_lists.datas.settings_data.MODEL.SettingsUI
import com.test.shopping_lists.datas.settings_data.SettingsRepository
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ItemList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ShoppingList
import com.test.shopping_lists.datas.shopping_actual_data.ShoppingActualDataRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListUsageViewModel(
    val actualDataRepository: ShoppingActualDataRepository,
    settingsRepository: SettingsRepository
) : ViewModel() {
    val selectedKey = settingsRepository.getSelectedKeyAsFlow()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = null)
    val settingsUI = settingsRepository.getSettingsUIAsFlow()
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = SettingsUI.DefaultSettings)

    val expandedListsIds = mutableStateListOf<Int>()
    val fullInfoShoppingLists = mutableStateMapOf<ShoppingList, ItemList?>() // most efficient way instead of stateflow

    val listNameTextStateFlow = MutableStateFlow("")

    val itemNameTextStateFlow = MutableStateFlow("")
    val itemNTextStateFlow = MutableStateFlow("")
    val itemNValueStateFlow = itemNTextStateFlow
        .mapLatest {
            it.toIntOrNull() ?: 0
        }
        .stateIn(viewModelScope, started = SharingStarted.Eagerly, initialValue = 0)

    init{
        loadData()
    }

    fun updateListName(name: String){
        listNameTextStateFlow.value = name
    }

    fun updateItemName(str: String){
        itemNameTextStateFlow.value = str
    }
    fun updateItemNText(str: String){
        if (str.toIntOrNull() != null || str.isEmpty()){
            itemNTextStateFlow.value = str
        }
    }

    fun updateExpanded(id: Int, expanded: Boolean){
        if (expanded){
            expandedListsIds += id
        }
        else expandedListsIds.remove(id)
    }

    fun autoExpandLists(){
        viewModelScope.launch {
            settingsUI.collect { s ->
                if (s.autoExpandLists) {
                    expandedListsIds.clear()
                    expandedListsIds += fullInfoShoppingLists.map{it.key.id}
                }
            }
        }
    }

    fun loadData(){
        viewModelScope.launch {
            try {
                selectedKey
                    .filterNotNull()
                    .collect { key ->
                        val lists = actualDataRepository.getAllMyShopLists(key.key)
                        if (settingsUI.value.autoExpandLists) {
                            expandedListsIds.clear()
                            expandedListsIds += lists.map { it.id }
                        }
                        fullInfoShoppingLists.clear()
                        lists.forEach {
                            fullInfoShoppingLists[it] = null
                            this.launch {
                                fullInfoShoppingLists[it] = actualDataRepository.getMyItemList(it.id)
                            }
                        }
                    }
            } catch (e: Exception) {
            }
        }
    }

    fun updateData(){
        viewModelScope.launch {
            try {
                val res = mutableMapOf<ShoppingList, ItemList?>()
                val lists = actualDataRepository.getAllMyShopLists(selectedKey.value?.key ?: "")
                coroutineScope { // structured concurrency waiting
                    lists.forEach {
                        this.launch {
                            res[it] = actualDataRepository.getMyItemList(it.id)
                        }
                    }
                }
                fullInfoShoppingLists.clear()
                fullInfoShoppingLists += res
            } catch (e: Exception) {
            }
        }
    }

    fun submitAddElement(listId: Int){
        viewModelScope.launch {
            try {
                println("###add")
                actualDataRepository.addItemToList(
                    listId,
                    itemNameTextStateFlow.value,
                    itemNValueStateFlow.value
                )
                updateData()
            } catch (e: Exception) {
            }
        }
    }

    fun removeItem(listId: Int, itemId: Int){
        viewModelScope.launch {
            try {
                println("###removing")
                actualDataRepository.removeFromItemList(listId, itemId)
                updateData()
            } catch (e: Exception) {
            }
        }
    }

    fun crossOfItem(itemId: Int){
        viewModelScope.launch {
            try {
                println("###crossing")
                actualDataRepository.crossOfElement(itemId)
                updateData()
            } catch (e: Exception) {
            }
        }
    }
    fun createList(){
        viewModelScope.launch {
            try {
                actualDataRepository.createShoppingList(
                    selectedKey.value?.key ?: "",
                    listNameTextStateFlow.value
                )
                updateData()
            } catch (e: Exception) {
            }
        }
    }
    fun removeList(listId: Int){
        viewModelScope.launch {
            try {
                actualDataRepository.removeShoppingList(listId)
                updateData()
            } catch (e: Exception) {
            }
        }
    }
}