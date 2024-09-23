package com.example.myapplication.models

import org.json.JSONArray

class Items {
    private var _items : MutableList<Item> = mutableListOf()

    constructor(json: JSONArray){
        for(item in 0 until json.length()){
            var item  = Item(json.getJSONObject(item))
            if(item.name != "") {
                _items.add(item)
            }
        }
    }

    fun getSortedList() : List<Item>{
        return _items.sortedWith(compareBy({it.listId}, {it.name})).toMutableList()
    }

    fun getItems() : MutableList<Item>{
        return _items
    }

}
