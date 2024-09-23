package com.example.myapplication.models

import org.json.JSONObject

// data class to hold the values of each of the items in the JSON
data class Item(private val json : JSONObject) {
    var id: Int = json.getInt("id")

    var listId: Int = json.getInt("listId")

    var name: String = if (json.isNull("name")) "" else json.getString("name")

}