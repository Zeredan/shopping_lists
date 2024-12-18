package com.test.shopping_lists.datas.shopping_actual_data

import com.test.shopping_lists.datas.shopping_actual_data.DTO.ItemListDTO
import com.test.shopping_lists.datas.shopping_actual_data.DTO.ShoppingListDTO
import com.test.shopping_lists.datas.shopping_actual_data.DTO.ShoppingListsDTO
import com.test.shopping_lists.datas.shopping_actual_data.DTO.toItemList
import com.test.shopping_lists.datas.shopping_actual_data.DTO.toShoppingList
import com.test.shopping_lists.datas.shopping_actual_data.DTO.toShoppingListsList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.Item
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ItemList
import com.test.shopping_lists.datas.shopping_actual_data.MODEL.ShoppingList
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.websocket.ws
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.parametersOf
import kotlinx.serialization.Serializable

interface ShoppingActualDataSource {
    suspend fun getAllMyShopLists(key: String) : List<ShoppingList>
    suspend fun getMyItemList(id: Int) : ItemList
    suspend fun addItemToList(listId: Int, name: String, n: Int)
    suspend fun removeFromItemList(listId: Int, itemId: Int)
    suspend fun crossOfElement(itemId: Int)
    suspend fun createKey() : String
    suspend fun authorize(key: String) : Boolean
    suspend fun createShoppingList(key: String, name: String) : Int
    suspend fun removeShoppingList(listId: Int) //maybe some list recovery
}

class ShoppingActualDataSourceRemoteImpl(
    val httpClient: HttpClient,
    val baseUrl: String
) : ShoppingActualDataSource{
    override suspend fun getAllMyShopLists(key: String): List<ShoppingList> {
        val res: ShoppingListsDTO = httpClient.get(
            urlString = "$baseUrl/GetAllMyShopLists"
        ) {
            parameter("key", key)
        }.body()

        return res.toShoppingListsList()
    }

    override suspend fun getMyItemList(id: Int): ItemList {
        val res: ItemListDTO = httpClient.get(
            urlString = "$baseUrl/GetShoppingList"
        ) {
            parameter("list_id", id)
        }.body()

        return res.toItemList()
    }

    override suspend fun addItemToList(listId: Int, name: String, n: Int) {
        httpClient.post(
            urlString = "$baseUrl/AddToShoppingList?id=$listId&value=$name&n=$n"
        )
        {
//            parameter("id", listId)
//            parameter("value", name)
//            parameter("n", n)
            /*parametersOf(
                "id" to listId,
                "value" to name,
                "n" to n
            )*/
        }
    }

    override suspend fun removeFromItemList(listId: Int, itemId: Int) {
        httpClient.post(
            urlString = "$baseUrl/RemoveFromList"
        )
        {
            parameter("list_id", listId)
            parameter("item_id", itemId)
            /*parametersOf(
                "list_id" to listId,
                "item_id" to itemId,
            )*/
        }
    }

    override suspend fun crossOfElement(itemId: Int) {
        httpClient.post(
            urlString = "$baseUrl/CrossItOff"
        )
        {
            parameter("id", itemId)
            /*parametersOf(
                "id" to itemId,
            )*/
        }
    }

    override suspend fun createKey() : String {
        return httpClient.get(
            urlString = "$baseUrl/CreateTestKey"
        ).bodyAsText()
    }

    override suspend fun authorize(key: String) : Boolean {
        @Serializable
        data class Resp(
            val success: Boolean
        )
        return httpClient.get(
            urlString = "$baseUrl/Authentication"
        )
        {
            parameter("key", key)
        }.body<Resp>().success
    }

    override suspend fun createShoppingList(key: String, name: String) : Int {
        @Serializable
        data class Resp(
            val success: Boolean,
            val list_id: Int
        )
        return httpClient.post(urlString = "$baseUrl/CreateShoppingList"){
            parameter("key", key)
            parameter("name", name)
        }.body<Resp>().list_id
    }

    override suspend fun removeShoppingList(listId: Int)  {
        httpClient.post(urlString = "$baseUrl/RemoveShoppingList"){
            parameter("list_id", listId)
        }
    }

}

class ShoppingActualDataSourceStubImpl : ShoppingActualDataSource{
    override suspend fun getAllMyShopLists(key: String): List<ShoppingList> {
        return listOf(
            ShoppingList(
                "1234",
                "lst1",
                1
            ),
            ShoppingList(
                "12354",
                "lst2",
                2
            ),
            ShoppingList(
                "12344",
                "lst3",
                3
            )
        )
    }

    override suspend fun getMyItemList(id: Int): ItemList {
        return ItemList(
            true,
            listOf(
                Item(
                    "1",
                    "alpaca",
                    3,
                    true
                ),
                Item(
                    "1",
                    "alpeaca",
                    5,
                    true
                ),
                Item(
                    "1",
                    "alrpaca",
                    4,
                    true
                )
            )
        )
    }

    override suspend fun addItemToList(listId: Int, name: String, n: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromItemList(listId: Int, itemId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun crossOfElement(itemId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun createKey(): String {
        TODO("Not yet implemented")
    }

    override suspend fun authorize(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun createShoppingList(key: String, name: String) : Int{
        TODO("Not yet implemented")
    }

    override suspend fun removeShoppingList(listId: Int) {
        TODO("Not yet implemented")
    }


}