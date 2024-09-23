package com.example.myapplication.models
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONArray
import java.net.URL
import kotlin.concurrent.thread

class WebData() : ViewModel() {
    private var _helper = MutableLiveData("[]")
    val helper : String
        get() = if (_helper.value == null) "" else (_helper.value as String)

    init {
        updateHelper()
    }


    private fun updateHelper(){
        var temp = "[]"
        thread {
            temp = try{
                URL("https://fetch-hiring.s3.amazonaws.com/hiring.json").readText()
            } catch (ex : Exception) {
                ""
            }
        }
        while (temp == "[]"){

        }
        _helper.value = temp
    }

    fun getJson() : JSONArray{
        if(helper == ""){
            return JSONArray() // empty JSONArray for when the user doesn't have internet
        }
        return JSONArray(helper)
    }
}

