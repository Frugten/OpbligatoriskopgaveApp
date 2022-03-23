package com.example.opbligatoriskopgaveapp.Login

import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.opbligatoriskopgaveapp.models.Item
import com.example.opbligatoriskopgaveapp.repository.ItemRepository

class LoginViewModel : ViewModel() {
    private val repository = ItemRepository()
    val itemsLiveData: LiveData<List<Item>> = repository.itemsLiveData
    val errorMessageLiveData: LiveData<String> = repository.errorMessageLiveData
    val updateMessageLiveData: LiveData<String> = repository.updateMessageLiveData

    init {
        reload()
    }

    fun reload() {
        repository.getPosts()
    }

    operator fun get(index: Int): Item? {
        return itemsLiveData.value?.get(index)
    }

    fun add(item: Item) {
        repository.add(item)
    }

    fun delete(id: Int) {
        repository.delete(id)
    }

    fun update(item: Item) {
        repository.update(item)
    }
}