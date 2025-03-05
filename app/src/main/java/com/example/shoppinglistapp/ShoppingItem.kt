package com.example.shoppinglistapp


data class ShoppingItem(
    val id :String ,
    var name :String ,
    var quantity :String ,
    var isEditing :Boolean = false
)
