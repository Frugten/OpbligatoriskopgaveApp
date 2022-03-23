package com.example.opbligatoriskopgaveapp

class Item(
    var id: Int = -1,
    var name: String,
    //var images: Int
) {
    override fun toString(): String {
        return "$id $name"
    }
}