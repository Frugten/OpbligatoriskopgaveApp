package com.example.opbligatoriskopgaveapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemsViewModel : ViewModel() {
   /* private var _nextId = 1
    private var _studentsList = mutableListOf(
        Item(_nextId++, "Stol"),
        Item(_nextId++, "Stol"),
        Item(_nextId++, "Stol"),
        Item(_nextId++, "Stol"),
        Item(_nextId++, "Stol"),
        Item(_nextId++, "Stol"),
        Item(_nextId++, "spisebord"),
        Item(_nextId++, "Bord")
    )
    private var _students = MutableLiveData<List<Item>>(_studentsList)
    val selected = MutableLiveData<Item>()

    var students: LiveData<List<Item>> = _students

    val adding: MutableLiveData<Boolean> = MutableLiveData(false)

    fun add(student: String) {
        student.id = _nextId++
        _studentsList.add(student)
        _students.value = _studentsList
    }

    fun remove(id: Int) {
        _studentsList.removeAll { student -> student.id == id }
    }

    fun update(id: Int, info: Item) {
        val student: Item = _studentsList.first { st -> st.id == id }
        student.name = info.name
        // _students.value = _studentsList
    }

    operator fun get(position: Int): Item { // [] operator overloading
        return _studentsList[position]
    }*/


}