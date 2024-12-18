package com.test.shopping_lists.datas.shopping_actual_data.DTO

import com.test.shopping_lists.datas.shopping_actual_data.MODEL.Item
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ItemList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ShoppingList

fun ItemDTO.toItem() : Item {
    return Item(
        created, name, id, is_crossed
    )
}

fun ItemListDTO.toItemList() : ItemList{
    return ItemList(
        success,
        item_list.map(ItemDTO::toItem)
    )
}

fun ShoppingListDTO.toShoppingList() : ShoppingList{
    return ShoppingList(
        created, name, id
    )
}

fun ShoppingListsDTO.toShoppingListsList() : List<ShoppingList>{
    return shop_list.map(ShoppingListDTO::toShoppingList)
}