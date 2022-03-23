package com.example.opbligatoriskopgaveapp.repository

import com.example.opbligatoriskopgaveapp.models.Item
import retrofit2.Call
import retrofit2.http.*

interface ItemStoreService {
    @GET("resaleitems")
    fun getAllItems(): Call<List<Item>>

    @GET("resaleitems/{bookId}")
    fun getItemById(@Path("bookId") id: Int): Call<Item>

    @POST("resaleitems")
    fun saveItem(@Body item: Item): Call<Item>

    @DELETE("resaleitems/{id}")
    fun deleteItem(@Path("id") id: Int): Call<Item>

    @PUT("resaleitems/{id}")
    fun updateItem(@Path("id") id: Int, @Body item: Item): Call<Item>
}