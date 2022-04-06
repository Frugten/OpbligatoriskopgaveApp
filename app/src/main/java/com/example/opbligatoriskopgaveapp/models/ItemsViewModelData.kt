package com.example.opbligatoriskopgaveapp.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.opbligatoriskopgaveapp.ItemsViewModel
import com.example.opbligatoriskopgaveapp.repository.ItemRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ItemsViewModelData : ViewModel() {
    private val repository = ItemRepository()
    val itemsLiveDataNew: MutableLiveData<List<Item>> = repository.itemsLiveData
    val itemsLiveData: LiveData<List<Item>> = itemsLiveDataNew
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getPosts()
    }
   /* fun getprice(Int): Item? {
        return itemsLiveData.value?.sortedByDescending { item -> item.price}?.get()
    }*/

    operator fun get(index: Int): Item? {
        return itemsLiveData.value?.get(index)
    }

    fun add(item: Item) {
        repository.add(item)
    }

    fun SortPriceASC() {
        itemsLiveDataNew.value = itemsLiveDataNew.value?.sortedBy { it.price }
    }

    fun SortDateASC() {
        itemsLiveDataNew.value = itemsLiveDataNew.value?.sortedBy { it.date }
    }

    fun FilterPriceASC(min: Int, max: Int) {
        itemsLiveDataNew.value = itemsLiveDataNew.value?.filter { i -> i.price in (min + 1) until max }
    }

    fun FilterSeller(currentUser: String) {
        itemsLiveDataNew.value = itemsLiveDataNew.value?.filter { i -> i.seller == currentUser }
    }

    fun delete(id: Int) {
        repository.delete(id)
    }


    fun update(item: Item) {
        repository.update(item)
    }
}