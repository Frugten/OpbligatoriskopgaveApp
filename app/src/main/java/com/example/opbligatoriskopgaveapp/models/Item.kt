package com.example.opbligatoriskopgaveapp.models

data class Item(val id: Int, val title: String, val price: Int, val description: String, val seller: String, val date: Int) {
    constructor(
        title: String,
        price: Int,
        description: String,
        seller: String,
        date: Int
    ) : this(-1, title, price, description, seller, date)

    override fun toString(): String {
        return "$id $title $price $description $seller $date"
    }
}

/*data class Item(val id: Int, val title: String, val price: Double) {
    constructor(title: String, price: Double) : this(-1, title, price)

    override fun toString(): String {
        return "$id  $title,  $price"
    }
}*/