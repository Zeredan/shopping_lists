package com.test.shopping_lists.datas.shopping_actual_data

import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ItemList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ShoppingList


class ShoppingActualDataRepository(
    val dataSource: ShoppingActualDataSource
) {
    suspend fun getAllMyShopLists(key: String) : List<ShoppingList> {
        return dataSource.getAllMyShopLists(key)
    }
    suspend fun getMyItemList(id: Int) : ItemList {
        return dataSource.getMyItemList(id)
    }
    suspend fun addItemToList(listId: Int, name: String, n: Int){
        dataSource.addItemToList(listId, name, n)
    }
    suspend fun removeFromItemList(listId: Int, itemId: Int){
        dataSource.removeFromItemList(listId, itemId)
    }
    suspend fun crossOfElement(itemId: Int){
        dataSource.crossOfElement(itemId)
    }
    suspend fun createKey() : String{
        return dataSource.createKey()
    }
    suspend fun authorize(key: String) : Boolean{
        return dataSource.authorize(key)
    }
    suspend fun createShoppingList(key: String, name: String) : Int{
        return dataSource.createShoppingList(key, name)
    }
    suspend fun removeShoppingList(listId: Int) {
        dataSource.removeShoppingList(listId)
    }
}